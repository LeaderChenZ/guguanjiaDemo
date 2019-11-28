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
                }
            },
            nodes: [],
            treeObj: {},
            rid: '',//授权角色的id
            checkedUser: [],//已授权角色的用户,
            showClass: 'hide',
            uids: ''
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
        update: function () {


        },
        toDelete: function (id) {

        },
        deleteById: function () {

        },
        save: function () {

        },
        initTree: function () { //初始化ZTree
            axios({
                url: 'manager/area'
            }).then(response => {
                this.nodes = response.data;
                this.treeObj = $.fn.zTree.init($("#treeMenu"), this.setting, this.nodes);
            }).catch(function (error) {
                layer.msg(error)
            });

        },
        onClick: function (event, treeId, treeNode) {
            this.params.aid = treeNode.id;
            this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
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