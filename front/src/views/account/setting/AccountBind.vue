<template>
  <CollapseContainer title="账号绑定" :canExpand="false">
    <List>
      <template v-for="item in accountBindList" :key="item.key">
        <ListItem>
          <ListItemMeta>
            <template #avatar>
              <Icon v-if="item.avatar" class="avatar" :icon="item.avatar" :color="item.color" />
            </template>
            <template #title>
              {{ item.title }}
              <Button type="link" size="small" v-if="item.extra" class="extra">
                {{ item.extra }}
              </Button>
            </template>
            <template #description>
              <div>{{ item.description }}</div>
            </template>
          </ListItemMeta>
        </ListItem>
      </template>
    </List>
  </CollapseContainer>
</template>
<script lang="ts" setup>
import { List, Button } from 'ant-design-vue';
import { CollapseContainer, Icon } from '@begcode/components';

import { accountBindList } from './data';

const ListItem = List.Item;
const ListItemMeta = List.Item.Meta;
</script>
<style lang="less" scoped>
.avatar {
  font-size: 40px !important;
}

.extra {
  float: right;
  margin-top: 10px;
  margin-right: 30px;
  cursor: pointer;
}
</style>
