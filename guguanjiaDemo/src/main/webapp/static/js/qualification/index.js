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
            type: '',
            check: ''
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: 'manager/qualification/index',
                method: "post",
                data: this.params
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        toUpdate: function (id) {
            axios({
                url: "manager/qualification/toUpdate",
                params: {id: id}
            }).then(response => {
                layer.obj = response.data;
                let index = layer.open({
                    type: 2,
                    title: '审核资质',
                    content: 'html/qualification/update.html',
                    area: ['80%', '60%'],
                    end: () => { //将then函数中的this传递到end的回调函数中
                        console.log("------");
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
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }

});