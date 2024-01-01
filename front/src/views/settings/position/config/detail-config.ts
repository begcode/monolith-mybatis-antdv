import { DescItem } from '@begcode/components';

// begcode-please-regenerate-this-file 如果您不希望重新生成代码时被覆盖，将please修改为don't ！！！

const fields: DescItem[] = [
  {
    label: 'ID',
    field: 'id',
    show: values => {
      return values && values.id;
    },
  },
  {
    label: '岗位代码',
    field: 'code',
  },
  {
    label: '名称',
    field: 'name',
  },
  {
    label: '排序',
    field: 'sortNo',
  },
  {
    label: '描述',
    field: 'description',
  },
];

export default {
  fields,
};
