<template>
  <Input v-bind="$attrs" :class="prefixCls" :size="size" :value="state">
    <template #addonAfter>
      <CountButton :size="size" :count="count" :value="state" :beforeStartFunc="sendCodeApi" />
    </template>
    <template #[item]="data" v-for="item in Object.keys($slots).filter(k => k !== 'addonAfter')">
      <slot :name="item" v-bind="data || {}"></slot>
    </template>
  </Input>
</template>
<script lang="ts" setup>
import { type DeepReadonly, PropType, type WritableComputedRef } from 'vue';
import { Input } from 'ant-design-vue';
import CountButton from './CountButton.vue';
import { useDesign } from '@/hooks/web/useDesign';
import { useRuleFormItem } from '@/hooks/component/useFormItemSingle';
import { SizeType } from 'ant-design-vue/es/config-provider';
import { FormItemContext } from 'ant-design-vue/es/form/FormItemContext';

defineOptions({ name: 'CountDownInput', inheritAttrs: false });

const props = defineProps({
  value: { type: String },
  size: { type: String as PropType<SizeType> },
  count: { type: Number, default: 60 },
  sendCodeApi: {
    type: Function as PropType<() => Promise<boolean>>,
    default: null,
  },
});

const { prefixCls } = useDesign('countdown-input');
const [state] = useRuleFormItem(props) as [WritableComputedRef<string>, (val: string) => void, DeepReadonly<any>, FormItemContext];
</script>
<style>
.vben-countdown-input .ant-input-group-addon {
  padding-right: 0;
  border: none;
  background-color: transparent;
}
.vben-countdown-input .ant-input-group-addon button {
  font-size: 14px;
}
</style>
