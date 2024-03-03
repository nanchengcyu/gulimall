<script setup xmlns="http://www.w3.org/1999/html">


import draggable from "element-ui/packages/color-picker/src/draggable";

export default {
  data() {
    return {
      draggable: false,
      maxLevel: 0,
      title: "",
      dialogType: "",
      category: {name: "", parentCid: 0, cateLevel: 0, showStatus: 1, sort: 0, catId: null, productUnit: "", icon: ""},
      dialogVisible: false,
      expendedKey: [],
      menus: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    };
  },
  methods: {
    batchSave() {

    },
    allowDrop(draggingNode, dropNode, type) {
      var level = countNodeLevel(draggingNode)
      return false;
    },
    countNodeLevel(draggingNode) {
    },

    submitData() {
      if (this.dialogType == "add") {
        this.addCategory();
      }
      if (this.dialogType == "edit") {
        this.editCategory();
      }

    },
    batchDelete() {
      let catIds = [];
      let checkedNodes = this.$refs.menuTree.getCheckedNodes();
      console.log("被选中的元素", checkedNodes);
      for (let i = 0; i < checkedNodes.length; i++) {
        catIds.push(checkedNodes[i].catId);
      }
      this.$confirm(`是否批量删除【${catIds}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/product/category/delete"),
            method: "post",
            data: this.$http.adornData(catIds, false)
          }).then(({ data }) => {
            this.$message({
              message: "菜单批量删除成功",
              type: "success"
            });
            this.getMenus();
          });
        })
        .catch(() => {});
    },
    editCategory() {
      var {catId, name, icon, productUnit} = this.category;
      this.$http({
        url: this.$http.adornUrl('/product/category/update'),
        method: 'post',
        data: this.$http.adornData({catId, name, icon, productUnit}, false)
      }).then(({data}) => {
        this.$message({
          message: '保存菜单成功',
          type: 'success'
        });
        //关闭对话框
        this.dialogVisible = false;
        //刷新菜单
        this.getMenus();
        //设置默认需要展开的菜单
        this.expendedKey = [this.category.parentCid]
      })
    },
    edit(data) {
      console.log("要修改的数据", data);
      this.dialogVisible = true;

      this.dialogType = "edit";
      this.title = "修改分类";
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: 'get',
      }).then(({data}) => {
        //请求成功后，从服务器获取的数据
        console.log("从服务器返回的数据", data)
        this.category.name = data.data.name;
        this.category.catId = data.data.catId;
        this.category.icon = data.data.icon;
        this.category.productUnit = data.data.productUnit;
        this.category.parentCid = data.data.parentCid;
      })

    },
    getMenus() {
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get',
      }).then(({data}) => {
        console.log("成功获取到菜单数据", data.data)
        this.menus = data.data;
      })

    },
    append(data) {
      console.log("append", data)
      this.title = "添加分类";
      this.dialogType = "add";
      this.dialogVisible = true;
      this.category.parentCid = data.catId;
      this.category.name = "";
      this.category.cateLevel = data.cateLevel * 1 + 1;
      this.category.sort = 0;
      this.category.showStatus = 1;
      this.category.icon = "";
      this.category.productUnit = "";
    },
    //添加三级分类
    addCategory() {
      console.log("提交的三级分类数据", this.category)
      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.category, false)

      }).then(({data}) => {
        this.$message({
          message: '保存菜单成功',
          type: 'success'
        });
        this.dialogVisible = false;
        //刷新菜单
        this.getMenus();
        //设置默认需要展开的菜单
        this.expendedKey = [this.category.parentCid]

      });

    },

    remove(node, data) {
      console.log("remove", node, data)
      var ids = [data.catId];
      this.$confirm(`是否删除【${data.name}】菜单?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl("/product/category/delete"),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          this.$message({
            message: '删除菜单成功',
            type: 'success'
          });
          //删除后刷新菜单 删除的不显示
          this.getMenus();
          //设置默认需要展开的菜单
          this.expendedKey = [node.parent.data.catId]
        })
      }).catch(() => {
      });

    },

  },
  created() {
    this.getMenus();
  },

};
</script>

<template>
  <div>
    <el-switch
      v-model="draggable"
      active-text="开启拖拽"
      inactive-text="关闭拖拽">
    </el-switch>
    <el-button @click="batchSave" v-if="draggable" type="primary">批量保存</el-button>
    <el-button type="danger" @click="batchDelete">批量删除</el-button>
    <el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId"
             :default-expanded-keys="expendedKey"
             :draggable="draggable"
             :allow-drop="allowDrop"
             ref="menuTree"><!--ref唯一标识-->
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)">
           增加
          </el-button>
            <el-button
              type="text"
              size="mini"
              @click="() => edit(data)">
            修改
          </el-button>
          <el-button v-if="node.childNodes.length==0"
                     type="text"
                     size="mini"
                     @click="() => remove(node, data)">
            删除
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="30%"
      :close-on-click-modal="false"

    >
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitData()">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">

</style>
