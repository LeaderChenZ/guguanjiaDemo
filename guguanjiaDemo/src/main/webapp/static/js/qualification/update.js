var vm = new Vue({
    el: '#main-container',
    data: {
        obj:{

        }
    },
    methods: {

        update: function (check) {
            this.obj.check = check;//修改审核状态
            axios({
                url:'manager/qualification/update',
                method:'post',
                data:this.obj
            }).then(response =>{
                parent.layer.msg(response.data.msg);

                let index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index)
            }).catch(function (error) {
                layer.msg(error);
            });

        },
        toDelete: function (id) {

        },
        deleteById: function () {

        },
        save: function () {

        },
    },
    created: function () {
        this.obj = parent.layer.obj;
    }

});