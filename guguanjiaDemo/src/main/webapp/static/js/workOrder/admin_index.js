var vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            pageInfo: {
                pageNum: 1,
                pageSize: 5
            },
            params:{
                officeName:'',
                status:''
            },
            treeObj: {},
            setting: {
                data: {
                    key: {
                        title: "fullName"
                    },
                    simpleData: {
                        enable: true,
                        pIdKey: "parent_id"
                    }

                },
                callback: {
                    onClick: this.onClick
                },
                view: {
                    fontCss: this.setCss
                }
            },
            nodes: [],
            name: '',
            node: ''
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {

            axios({
                url: 'manager/admin/work',
                data: {
                    pageNum: pageNum,
                    pageSize: pageSize
                },
                method: "post",
                data:this.params
            }).then(response => {
                console.log(response.data);
                this.pageInfo = response.data;
            }).catch(function (error) {
                console.log(error)
            })

        },
        selectOne: function (id) {
            axios({
                url: 'manager/admin/work/detail',
                params: {id: id}
            }).then(response => {
                console.log(response.data);
                layer.workDetail = response.data;
                let index = layer.open({
                    type: 2,
                    title: '详情',
                    content: 'html/work/work-detail.html',
                    area: ['80%', '80%']
                });
            }).catch(function (error) {
                console.log(error)
            });
        }
        ,
        initTree: function () {
            axios({
                url: 'manager/office/list'
            }).then(response => {
                this.nodes = response.data;
                let treeObj = $.fn.zTree.init($("#pullDownTreeone"), this.setting, this.nodes);
                treeObj.expandAll(true);//展开所有节点
                this.treeObj = treeObj;
                console.log(treeObj)
            }).catch(function (error) {
                layer.msg(error)
            });
        },
        onClick:function(event, treeId, treeNode) {
            console.log(treeId);
            console.log(treeNode);
            this.name = treeNode.name;
            this.node = treeNode;
            this.params.officeName=this.name;
            this.selectAll();
        },
        search: function () {
            let nodes = this.treeObj.getNodesByParamFuzzy("name", this.name, null);
            let treeNodes = this.treeObj.transformToArray(this.treeObj.getNodes());


            //清楚原高亮标志
            for (let index in treeNodes) {
                treeNodes[index].higtLine = false;
                this.treeObj.updateNode(treeNodes[index]);//更新节点，自动调用清楚css
            }

            for (let index in treeNodes) {
                for (let nodeIndex in nodes) {
                    if (treeNodes[index].id == nodes[nodeIndex].id) {
                        treeNodes[index].higtLine = true;
                        this.treeObj.updateNode(treeNodes[index]);
                    }
                }
            }
        },
        setCss: function (treeId, treeNode) {
            return treeNode.higtLine ? {color: "red"} : {color: ''};//根据标记显示高亮
        },
        selectAllNoCondition:function (pageNum, pageSize) {
            this.name='';
            this.params={officeName:'',status:''};
            this.selectAll(pageNum,pageSize);
        }
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },
    mounted: function () {
        this.initTree();
    }

});