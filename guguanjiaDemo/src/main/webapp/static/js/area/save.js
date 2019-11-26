var vm = new Vue({
    el: '#main-container',
    data: {
        obj: {},

    },
    methods: {
        selectAll: function (pageNum, pageSize) {

        },

        update: function () {
            console.log(this.obj);
            axios({
                url: 'manager/area/update',
                method: "post",
                data: this.obj
            }).then(response => {
                parent.location.reload();
                let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index);
                parent.layer.msg(response.data.msg);
            }).catch(function (error) {
                layer.msg(error)
            })


        },

        selectArea: function () {

            let index = layer.open({
                type: 2,
                title: "修改上级区域",
                content: "html/area/select.html",
                area: ['80%', '60%'],
                end: () => {
                    console.log(this.obj)
                }

            })

        },
        selectIcon: function () {
            let index = layer.open({
                type: 2,
                title: "修改图标",
                content: "html/modules/font-awesome.html",
                area: ['80%', '70%'],
                end: () => {
                    this.obj.icon = layer.icon;
                }
            })
        }
    },
    created: function () {
        this.obj = parent.layer.obj;
        this.obj.oldParentIds = this.obj.parentIds;

        /*if (this.obj.parentName != '') {
            this.obj.parentName = layer.parentName;
        }*/
        console.log(this.obj)
    }

});