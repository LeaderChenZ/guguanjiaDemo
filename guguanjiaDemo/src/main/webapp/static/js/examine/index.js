let vm = new Vue({
    el: '#main-container',
    data: function () {
        return {

            pageInfo: {
                pageNum: 1,
                pageSize: 5
            }, treeObj: {},

            setting: {//ztree设置对象
                data: {
                    key: {
                        title: "fullName"//将treeNode的fullName作为节点的title  ztree对象下面的节点对象就叫treeNode
                    },
                    simpleData: {
                        enable: true, //使用极简数组模式传入nodes给ztree
                        pIdKey: "parent_id"

                    }
                },

                callback: {
                    // beforeClick: this.beforeClick,
                    onClick: this.onClick
                },
                view: {
                    fontCss: this.setCss
                }


            },
            nodes: [],
            node: '',
            name: ''

        }
    },
    methods: {
        //初始化ztree对象
        initTree: function () {
            axios({
                url: "manager/office/list"
            }).then(response => {
                this.nodes = response.data;  //赋值nodes
                let treeObj = $.fn.zTree.init($("#pullDownTreeone"), this.setting, this.nodes);
                treeObj.expandAll(true);
                this.treeObj = treeObj;//给vue传值
                console.log(treeObj)
            }).catch(function (error) {
                layer.msg(error);
            });

        },
        selectAll: function (ageNum, pageSize) {

        },
        onClick: function (event, treeId, treeNode) {
            console.log(treeId);
            console.log(treeNode);
            this.name = treeNode.name;
            this.node = treeNode;
        },
        search: function () { //搜索处理
            //模糊查询
            let nodes = this.treeObj.getNodesByParamFuzzy("name", this.name, null);

            let treeNodes = this.treeObj.transformToArray(this.treeObj.getNodes());

            //更新节点   自动调用清楚css
            for (let index in treeNodes) {
                treeNodes[index].higtLine = false;
                this.treeObj.updateNode(treeNodes[index]);
            }

            for (let index in treeNodes) {
                console.log(treeNodes[index])
                for (let nodeIndex in  nodes) {
                    if (treeNodes[index].id == nodes[nodeIndex].id) {
                        treeNodes[index].higtLine = true;
                        //更新节点  会触动自动的设置css回调
                        this.treeObj.updateNode(treeNodes[index]);
                    }
                }
            }

        },
        setCss: function (treeId, treeNode) {
            return treeNode.higtLine ? {color: "red"} : {color: ""};//根据标记显示高亮

        }
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }
    ,
    mounted: function () { //dom节点挂载
        this.initTree();
    }
})