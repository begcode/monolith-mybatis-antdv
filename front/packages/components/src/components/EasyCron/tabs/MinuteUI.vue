<template>
  <div :class="`${prefixCls}-config-list`">
    <RadioGroup v-model:value="type">
      <div class="item">
        <Radio :value="TypeEnum.every" v-bind="beforeRadioAttrs">每分</Radio>
      </div>
      <div class="item">
        <Radio :value="TypeEnum.range" v-bind="beforeRadioAttrs">区间</Radio>
        <span> 从 </span>
        <InputNumber v-model:value="valueRange.start" v-bind="typeRangeAttrs" />
        <span> 分 至 </span>
        <InputNumber v-model:value="valueRange.end" v-bind="typeRangeAttrs" />
        <span> 分 </span>
      </div>
      <div class="item">
        <Radio :value="TypeEnum.loop" v-bind="beforeRadioAttrs">循环</Radio>
        <span> 从 </span>
        <InputNumber v-model:value="valueLoop.start" v-bind="typeLoopAttrs" />
        <span> 分开始，间隔 </span>
        <InputNumber v-model:value="valueLoop.interval" v-bind="typeLoopAttrs" />
        <span> 分 </span>
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
import { InputNumber, RadioGroup, CheckboxGroup, Checkbox, Radio } from 'ant-design-vue';
import { useTabProps, useTabEmits, useTabSetup } from './useTabMixin';

export default defineComponent({
  name: 'MinuteUI',
  components: { InputNumber, RadioGroup, CheckboxGroup, Checkbox, Radio },
  props: useTabProps({
    defaultValue: '*',
  }),
  emits: useTabEmits(),
  setup(props, context) {
    return useTabSetup(props, context, {
      defaultValue: '*',
      minValue: 0,
      maxValue: 59,
      valueRange: { start: 0, end: 59 },
      valueLoop: { start: 0, interval: 1 },
    });
  },
});
</script>
