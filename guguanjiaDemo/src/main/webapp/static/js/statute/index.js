var vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },
        params:{
            pageNum: '',
            pageSize: ''
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "manager/statute/index",
                method: "post",
                data: this.params
            }).then(response => {
                console.log(response.data);
                // if(response.data.list.length>0){
                this.pageInfo = response.data;
                // }

            }).catch(function (error) {
                layer.msg(error);
            });


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
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }

});