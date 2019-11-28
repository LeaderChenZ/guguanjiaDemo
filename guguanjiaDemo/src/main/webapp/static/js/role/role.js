var vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            pageInfo: {
                pageNum: 1,
                pageSize: 5
            },
            params: {
                pageNum: '',
                pageSize: '',
                dataScope: '',//默认值，让下拉出现的时候默认被选中
                oid: '',
                name: '',
                remarks: '',
                officeName: ''
            },

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
            rid: ''
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: 'manager/role/list',
                method: 'post',
                data: this.params
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(function (error) {
                layer.msg(error);
            })
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
        manageUser: function (rid) {
            layer.rid = rid;
            let index = layer.open({
                type: 2,
                title: "用户角色授权",
                content: 'html/role/role-user.html',
                area: ['80%', '80%']

            })

        },
        toDelete: function (id) {

        },
        deleteById: function () {

        },
        save: function () {

        }
        ,
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
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },
    mounted: function () {
        this.initTree();
    }

});