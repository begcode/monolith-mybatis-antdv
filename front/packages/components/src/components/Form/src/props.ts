import type { CSSProperties, PropType } from 'vue';
import type { RowProps } from 'ant-design-vue/lib/grid/Row';
import dayjs from 'dayjs';
import type { FieldMapToNumber, FieldMapToTime, FormSchema } from './types/form';
import type { ColEx } from './types';
import type { Fn } from '#/types';
import type { Recordable } from '#/utils';
import { propTypes } from '@/utils/propTypes';
import { ButtonProps } from '@/components/Button';

const form = {
  labelCol: {
    xs: { span: 24 },
    sm: { span: 4 },
    xl: { span: 6 },
    xxl: { span: 4 },
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 18 },
  },
  //表单默认冒号
  colon: true,
};
export const basicProps = {
  model: {
    type: Object as PropType<Recordable>,
    default: () => ({}),
  },
  // 标签宽度  固定宽度
  labelWidth: {
    type: [Number, String] as PropType<number | string>,
    default: 0,
  },
  fieldMapToTime: {
    type: Array as PropType<FieldMapToTime>,
    default: () => [],
  },
  fieldMapToNumber: {
    type: Array as PropType<FieldMapToNumber>,
    default: () => [],
  },
  compact: propTypes.bool,
  // 表单配置规则
  schemas: {
    type: Array as PropType<FormSchema[]>,
    default: () => [],
  },
  mergeDynamicData: {
    type: Object as PropType<Recordable>,
    default: null,
  },
  baseRowStyle: {
    type: Object as PropType<CSSProperties>,
  },
  baseColProps: {
    type: Object as PropType<Partial<ColEx>>,
  },
  autoSetPlaceHolder: propTypes.bool.def(true),
  // 在INPUT组件上单击回车时，是否自动提交
  autoSubmitOnEnter: propTypes.bool.def(false),
  submitOnReset: propTypes.bool,
  submitOnChange: propTypes.bool,
  size: propTypes.oneOf(['default', 'small', 'large']).def('default'),
  // 禁用表单
  disabled: propTypes.bool,
  emptySpan: {
    type: [Number, Object] as PropType<number | Recordable>,
    default: 0,
  },
  // 是否显示收起展开按钮
  showAdvancedButton: propTypes.bool,
  // 转化时间
  transformDateFunc: {
    type: Function as PropType<Fn>,
    default: (date: any) => {
      return dayjs.isDayjs(date) ? date?.format?.('YYYY-MM-DD HH:mm:ss') : date;
    },
  },
  rulesMessageJoinLabel: propTypes.bool.def(true),
  // 超过3列自动折叠
  autoAdvancedCol: propTypes.number.def(3),
  // 超过3行自动折叠
  autoAdvancedLine: propTypes.number.def(3),
  // 不受折叠影响的行数
  alwaysShowLines: propTypes.number.def(1),

  // 是否显示操作按钮
  showActionButtonGroup: propTypes.bool.def(true),
  // 操作列Col配置
  actionColOptions: Object as PropType<Partial<ColEx>>,
  // 显示重置按钮
  showResetButton: propTypes.bool.def(true),
  // 是否聚焦第一个输入框，只在第一个表单项为input的时候作用
  autoFocusFirstItem: propTypes.bool,
  // 重置按钮配置
  resetButtonOptions: Object as PropType<Partial<ButtonProps>>,

  // 显示确认按钮
  showSubmitButton: propTypes.bool.def(true),
  // 确认按钮配置
  submitButtonOptions: Object as PropType<Partial<ButtonProps>>,

  // 自定义重置函数
  resetFunc: Function as PropType<() => Promise<void>>,
  submitFunc: Function as PropType<() => Promise<void>>,

  // 以下为默认props
  hideRequiredMark: propTypes.bool,

  labelCol: {
    type: Object as PropType<Partial<ColEx>>,
    default: form.labelCol,
  },

  layout: propTypes.oneOf(['horizontal', 'vertical', 'inline']).def('horizontal'),
  tableAction: {
    type: Object,
  },

  wrapperCol: {
    type: Object as PropType<Partial<ColEx>>,
    default: form.wrapperCol,
  },

  colon: propTypes.bool.def(form.colon),

  labelAlign: propTypes.string,

  rowProps: Object as PropType<RowProps>,

  // 当表单是查询条件的时候 当表单改变后自动查询，不需要点击查询按钮
  autoSearch: propTypes.bool.def(false),
};
