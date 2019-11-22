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
                areaName: '',
                aid: 1
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
            treeObj: {}
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: 'manager/area/index',
                method: 'post',
                data: this.params
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        toUpdate: function (id) {

        },
        update: function () {


        },
        toDelete: function (id) {

        },
        deleteById: function () {

        },
        save: function () {

        },
        exportExcel: function () {
            location.href = 'manager/area/exportExcel'
        }, importExcel: function (e) {
            console.log(e.target); //获取事件源对象  input
            let file = e.target.files[0]; //获取上传的文件对象
            let form = new FormData();//构建表单对象
            form.append("file", file);//绑定file对象到key file上  key必须要与后台的接收参数名一致

            //获取nodes
            axios({
                url: 'manager/area/importExcel',
                method: "post",
                headers: {
                    "content-type": 'multipart/form-data',//设置请求头为文件上传
                },
                data: form
            }).then(response => {
                layer.msg(response.data.msg);
            }).catch(function (error) {
                console.log(error)
            })

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