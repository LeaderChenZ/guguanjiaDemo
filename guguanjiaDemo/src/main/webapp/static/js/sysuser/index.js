var vm = new Vue({
    el: '#main-container',
    data: {

        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },
        params: {
            pageNum: '',
            pageSize: '',
        },
        name: '',
        uid: '',
        oid: '',
        rid: ''

    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            // this.params.name = $("#")
            axios({
                url: 'manager/sysuser/list',
                method: 'post',
                data: this.params
            }).then(response => {
                this.pageInfo = response.data;
                console.log(response.data)
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
        update: function () {


        },
        toDelete: function (id) {

        },
        deleteById: function () {

        },
        save: function () {

        },
        selectAllClean:function (pageNum,pageSize) {
        this.name='';
        this.oid='';
        this.uid='';
        this.rid='';
        this.selectAll(pageNum,pageSize)
        }

    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }

});