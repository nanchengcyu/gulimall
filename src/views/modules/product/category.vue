<script setup xmlns="http://www.w3.org/1999/html">


import {data} from "autoprefixer";

export default {
  data() {
    return {
      category: {name: "", parentCid: 0, cateLevel: 0, showStatus: 1, sort: 0},
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
      this.dialogVisible = true
      this.category.parentCid = data.catId;
      this.category.cateLevel = data.cateLevel * 1 + 1;
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
        this.dialogVisible=false;
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
    <el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId"
             :default-expanded-keys="expendedKey">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)">
            Append
          </el-button>
          <el-button v-if="node.childNodes.length==0"
                     type="text"
                     size="mini"
                     @click="() => remove(node, data)">
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addCategory">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">

</style>
