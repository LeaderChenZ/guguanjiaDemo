let vm = new Vue({
    el:'#main-container',
    data:{
        pageInfo:{
            pageNum:1,//默认值
            pageSize:5
        },
        appVersion:''
    },
    methods:{
        selectAll:function(pageNum,pageSize){
            //查询后台，返回分页数据，更新vue的pageInfo对象

            axios({
                url:'manager/app/index',
                params:{
                    pageNum:pageNum,
                    pageSize:pageSize
                }
            }).then(response => {
                console.log(response.data);
                this.pageInfo = response.data;
                // this.$set(this.pageInfo,response.data);
            }).catch(function (error) {
                console.log(error);
            })
        },
        toUpdate:function(id){
            axios({
                url:'manager/app/toUpdate',
                params: {id:id}
            }).then(response => {
                // console.log(response.data);
                layer.appVersion = response.data;//返回数据，绑定到layer上，传递给子窗口
                console.log(layer)
                let index = layer.open({
                    type:2,
                    title:'更新app',
                    content:'html/app/update.html',
                    area:['80%','80%'],
                    end: () => {//将then函数中的this传递到end的回调函数中
                        //刷新页面数据    1.直接查询selectAll实现    2.获取layer.appVersion更新当前pageInfo的该数据
                        this.selectAll(this.pageInfo.pageNum,this.pageInfo.pageSize);
                    }
                });
            }).catch(function (error) {
                console.log(error);
            })
        }
    },
    created:function (){
        this.selectAll();//在vue创建后调用函数返回数据
    }
});