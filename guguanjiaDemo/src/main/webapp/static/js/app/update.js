let vm = new Vue({
    el: '#main-container',
    data: {
        appVersion: ''
    },
    methods:{
        update: function (){
            axios({
                url: 'manager/app/update',
                method: 'post',
                data: this.appVersion
            }).then(response => {
                parent.layer.msg(response.data.msg);//通过父窗口layer对象弹框
                console.log("关闭当前窗口");
                let index = parent.layer.getFrameIndex(window.name);//先得到当前iframe层的索引
                parent.layer.close(index);


            }).catch(function (error) {
                console.log(error)
            })
        }
    },
    created: function () {
        this.appVersion = parent.layer.appVersion;
        // this.update1();
    }
})