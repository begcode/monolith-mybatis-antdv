<template>
  <div :class="`${prefixCls}-config-list`">
    <RadioGroup v-model:value="type">
      <div class="item">
        <Radio :value="TypeEnum.every" v-bind="beforeRadioAttrs">每月</Radio>
      </div>
      <div class="item">
        <Radio :value="TypeEnum.range" v-bind="beforeRadioAttrs">区间</Radio>
        <span> 从 </span>
        <InputNumber v-model:value="valueRange.start" v-bind="typeRangeAttrs" />
        <span> 月 至 </span>
        <InputNumber v-model:value="valueRange.end" v-bind="typeRangeAttrs" />
        <span> 月 </span>
      </div>
      <div class="item">
        <Radio :value="TypeEnum.loop" v-bind="beforeRadioAttrs">循环</Radio>
        <span> 从 </span>
        <InputNumber v-model:value="valueLoop.start" v-bind="typeLoopAttrs" />
        <span> 月开始，间隔 </span>
        <InputNumber v-model:value="valueLoop.interval" v-bind="typeLoopAttrs" />
        <span> 月 </span>
      </div>
      <div class="item">
        <Radio :value="TypeEnum.specify" v-bind="beforeRadioAttrs">指定</Radio>
        <div class="list">
          <CheckboxGroup v-model:value="valueList">
            <template v-for="i in specifyRange" :key="i">
              <Checkbox :value="i" v-bind="typeSpecifyAttrs">{{ i }}</Checkbox>
            </template>
          </CheckboxGroup>
        </div>
      </div>
    </RadioGroup>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { InputNumber, Radio, RadioGroup, Checkbox, CheckboxGroup } from 'ant-design-vue';
import { useTabProps, useTabEmits, useTabSetup } from './useTabMixin';

export default defineComponent({
  name: 'MonthUI',
  components: { InputNumber, Radio, RadioGroup, Checkbox, CheckboxGroup },
  props: useTabProps({
    defaultValue: '*',
  }),
  emits: useTabEmits(),
  setup(props, context) {
    return useTabSetup(props, context, {
      defaultValue: '*',
      minValue: 1,
      maxValue: 12,
      valueRange: { start: 1, end: 12 },
      valueLoop: { start: 1, interval: 1 },
    });
  },
});
</script>
