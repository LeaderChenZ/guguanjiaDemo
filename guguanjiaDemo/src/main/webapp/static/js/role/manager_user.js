var vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            setting: {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback: {
                    onClick: this.onClick
                },
                view: {
                    fontCss: this.setCss
                }
            },
            nodes: [],
            treeObj: {},
            treeNode: {},
            rid: '',//授权角色的id
            checkedUser: [],//已授权角色的用户,
            companyUsers: [],//公司未授权当前角色的用户
            showClass: 'hide',//显示隐藏移除授权按钮
            companyShowClass: 'hide',////显示隐藏授权按钮
            uids: [],//需要移除角色授权的人员id数组
            cids: [],
        }
    },
    methods: {
        //根据当前角色id，查询后台，得到当前角色已经授权的用户id和name
        selectRealUser: function () {
            axios({
                url: 'manager/sysuser/selectByRid',
                params: {rid: this.rid}
            }).then(response => {
                this.checkedUser = response.data;
                //给每个用户绑定新属性show，用于是否已控制
                for (let i = 0; i < this.checkedUser.length; i++) {
                    this.checkedUser[i].show = false;
                }
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        changeShow: function (id) {
            //改动被选中的赋值
            for (let i = 0; i < this.checkedUser.length; i++) {
                //被选中的就赋值为它的相反数
                if (this.checkedUser[i].id == id) {
                    this.checkedUser[i].show = !this.checkedUser[i].show;
                    if (this.checkedUser[i].show) {
                        this.uids.push(this.checkedUser[i].id);//将找到的需要移除人员的id放入uids中

                        this.showClass = 'show';//修改显示提交按钮
                        return;
                    }
                }
            }
            if ($("#yxuser input:checked").length == 0) { //如果没有任何的input被选中，就隐藏提交按钮
                this.showClass = 'hide';
            }

        }, removeUser: function () {
            let params = {rid: this.rid, uids: this.uids};
            axios({
                ul: "manager/role/updateByUids",
                method: "post",
                data: params
            }).then(res => {
                this.selectRealUser();
                this.showClass = 'hide';
                layer.msg(res.data.msg)
            }).catch(function (err) {
                layer.msg(err);
            })
        },
        changeCompanyShow: function (id) {

        },
        toUpdate: function (id) {
            axios({
                url: 'manager/area/toUpdate',
                params: {areaId: id}
            }).then(response => {
                layer.obj = response.data;
                let index = layer.open({
                    type: 2,
                    title: '区域修改',
                    content: "html/area/save.html",
                    area: ["80%", "80%"],
                    end: () => {  //将then函数中的this传递到end的回调函数中
                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);

                    }
                })
            }).catch(function (error) {
                layer.msg(error)
            })
        },
        initTree: function () { //初始化ZTree
            axios({
                url: 'manager/office/list'
            }).then(response => {
                this.nodes = response.data;
                this.nodes[this.nodes.length] = {
                    "id": 0,
                    "name": "所有机构"
                }
                this.treeObj = $.fn.zTree.init($("#treeOffice"), this.setting, this.nodes);
            }).catch(function (error) {
                layer.msg(error)
            });

        },
        onClick: function (event, treeId, treeNode) {
            this.treeNode = treeNode;
            let treeNodes = this.treeObj.transformToArray(this.treeObj.getNodes());
            //清楚原高亮标记
            for (let index in treeNodes) {
                if (treeNodes[index].id == treeNode.id) {
                    treeNodes[index].higtLine = true;//设置高亮标志
                } else {
                    treeNodes[index].higtLine = false;
                }
                this.treeObj.updateNode(treeNodes[index]);
            }
            this.dxUser();
        },
        setCss: function (treeId, treeNode) {
            return treeNode.higtLine ? {color: "red"} : {color: ''};//根据标记显示高亮
        },
        dxUser: function () {
            //根据公司id，角色id 查询出当前选中公司的未给当前角色授权的用户
            axios({
                url: 'manager/sysuser/selectNoRole',
                params: {
                    oid: this.treeNode.id, rid: this.rid
                }
            }).then(res => {
                this.companyUsers = res.data;
                //给每个用户绑定新属性show，用于控制被选中与否
                for (let i = 0; i < this.companyUsers.length; i++) {
                    this.companyUsers[i].show = false;
                }
            }).catch(function (error) {
                layer.msg(error)
            })
        }
    },
    created: function () {
        this.rid = parent.layer.rid;
    },
    mounted: function () {
        this.initTree();
        this.selectRealUser();
    }

});