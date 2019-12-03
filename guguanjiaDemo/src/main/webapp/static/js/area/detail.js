var vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            setting: {
                data: {
                    key: {
                        title: "fullName"
                    },
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
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
            treeObj: {},
            tempNodes: [],
            node: '',
            name: '',
            parentId:'',
            parentIds:'',
            parentName: ''

        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {

        },
        initTree: function () {
            axios({
                url: 'manager/area/list'
            }).then(response => {
                this.nodes = response.data;
                this.treeObj = $.fn.zTree.init($("#select-tree"), this.setting, this.nodes);
                treeObj.expandAll(true);
                this.treeObj = treeObj;
            }).catch(function (error) {
                console.log(error);
            })
        },
        onClick: function (event, treeId, treeNode) {
            this.parentName = treeNode.name;
            this.name = treeNode.name;
            this.node = treeNode;

            if (treeNode.id === 0) return;
            let tempIds = treeNode.id + ",";
            var temp = treeNode.getParentNode();
            while (tempIds.substring(0, 2) !== "1,") {
                tempIds = temp.id + "," + tempIds;
                temp = temp.getParentNode();
            }
            this.parentIds = "0," + tempIds;
            this.parentId = treeNode.id;

        },
        search: function () { //搜索处理

            if (this.name == '') {
                return;
            }
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

        },
        closeDetail:function () {
            if (this.parentId !== "")
                parent.layer.parentId = this.parentId;
            if (this.parentName !== "")
                parent.layer.parentName = this.parentName;
            if (this.parentIds !== "")
                parent.layer.parentIds = this.parentIds;
            let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
        }
    },
    mounted: function () {
        this.initTree();
    }

});