<template>
  <div v-for="(param, index) in dynamicInput.params" :key="index" style="display: flex">
    <Input placeholder="请输入参数key" v-model:value="param.label" style="width: 30%; margin-bottom: 5px" @input="emitChange" />
    <Input placeholder="请输入参数value" v-model:value="param.value" style="width: 30%; margin: 0 0 5px 5px" @input="emitChange" />
    <MinusCircleOutlined
      v-if="dynamicInput.params.length > min"
      class="dynamic-delete-button"
      @click="remove(param)"
      style="width: 50px"
    ></MinusCircleOutlined>
  </div>
  <div>
    <Button type="dashed" style="width: 60%" @click="add">
      <PlusOutlined />
      新增
    </Button>
  </div>
</template>
<script lang="ts" setup>
import { reactive, UnwrapRef, watchEffect } from 'vue';
import { Button, Input } from 'ant-design-vue';
import { MinusCircleOutlined, PlusOutlined } from '@ant-design/icons-vue';
import { propTypes } from '@/utils/propTypes';
import { isEmpty } from '@/utils/is';

interface Params {
  label: string;
  value: string;
}

defineOptions({
  name: 'AddInput',
});

const props = defineProps({
  value: propTypes.string.def(''),
  //自定义删除按钮多少才会显示
  min: propTypes.integer.def(1),
});

const emit = defineEmits(['change', 'update:value']);

//input动态数据
const dynamicInput: UnwrapRef<{ params: Params[] }> = reactive({ params: [] });
//删除Input
const remove = (item: Params) => {
  let index = dynamicInput.params.indexOf(item);
  if (index !== -1) {
    dynamicInput.params.splice(index, 1);
  }
  emitChange();
};
//新增Input
const add = () => {
  dynamicInput.params.push({
    label: '',
    value: '',
  });
  emitChange();
};

//监听传入数据value
watchEffect(() => {
  initVal();
});

/**
 * 初始化数值
 */
function initVal() {
  console.log('props.value', props.value);
  dynamicInput.params = [];
  if (props.value && props.value.indexOf('{') == 0) {
    let jsonObj = JSON.parse(props.value);
    Object.keys(jsonObj).forEach(key => {
      dynamicInput.params.push({ label: key, value: jsonObj[key] });
    });
  }
}
/**
 * 数值改变
 */
function emitChange() {
  let obj = {};
  if (dynamicInput.params.length > 0) {
    dynamicInput.params.forEach(item => {
      obj[item['label']] = item['value'];
    });
  }
  emit('change', isEmpty(obj) ? '' : JSON.stringify(obj));
  emit('update:value', isEmpty(obj) ? '' : JSON.stringify(obj));
}
</script>
<style scoped>
.dynamic-delete-button {
  cursor: pointer;
  position: relative;
  top: 4px;
  font-size: 24px;
  color: #999;
  transition: all 0.3s;
}

.dynamic-delete-button:hover {
  color: #777;
}

.dynamic-delete-button[disabled] {
  cursor: not-allowed;
  opacity: 0.5;
}
</style>
