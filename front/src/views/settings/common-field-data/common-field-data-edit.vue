<script lang="tsx">
import { getCurrentInstance, reactive, computed, defineComponent, h, ref, resolveComponent, Component, nextTick, onUnmounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { message, Space, Tabs, TabPane, CollapsePanel, Collapse, Card } from 'ant-design-vue';
import { isBoolean, isFunction } from 'lodash-es';

import config from './config/edit-config';
import { CommonFieldData, ICommonFieldData } from '@/models/settings/common-field-data.model';

import ServerProvider from '@/api-service/index';
import { BasicForm, Button, Icon, PageWrapper } from '@begcode/components';
import { useGo } from '@/hooks/web/usePage';
import { useMultipleTabStore } from '@/store/modules/multipleTab';

export default defineComponent({
  // begcode-please-regenerate-this-file 如果您不希望重新生成代码时被覆盖，将please修改为don't ！！！
  name: 'SystemCommonFieldDataEdit',
  props: {
    entityId: {
      type: [String, Number] as PropType<string | number>,
      default: '',
    },
    containerType: {
      type: String,
      default: 'router',
    },
    baseData: {
      type: Object,
      default: () => ({}),
    },
    savedOpen: {
      type: Boolean,
      default: false,
    },
  },
  components: {
    BasicForm,
  },
  async setup(props) {
    const ctx = getCurrentInstance()?.proxy;
    const apiService = ctx?.$apiService as typeof ServerProvider;
    const relationshipApis: any = {
      siteConfig: apiService.settings.siteConfigService.retrieve,
      dictionary: apiService.settings.dictionaryService.retrieve,
    };
    const route = useRoute();
    const router = useRouter();
    const go = useGo();
    const tabStore = useMultipleTabStore();
    const activeNames = ref<any[]>([]);
    const handleChange = (val: any[]) => {
      activeNames.value = val;
      ctx?.$emit('change', activeNames.value);
    };
    let commonFieldDataId = ref('');
    if (props.containerType === 'router') {
      commonFieldDataId.value = route.params?.entityId as string;
    } else {
      commonFieldDataId.value = props.entityId as string;
    }
    const commonFieldData = ref<ICommonFieldData>(new CommonFieldData());
    commonFieldData.value = Object.assign(commonFieldData.value, props.baseData);
    if (commonFieldDataId.value) {
      const data = await apiService.settings.commonFieldDataService.find(Number(commonFieldDataId.value));
      if (data) {
        commonFieldData.value = Object.assign(commonFieldData.value, data);
      }
    }
    const formItemsConfig = config.fields();
    const submitButtonTitlePrefix = commonFieldDataId.value ? '更新' : '保存';
    const saveOrUpdateApi = commonFieldDataId.value
      ? apiService.settings.commonFieldDataService.update
      : apiService.settings.commonFieldDataService.create;
    const saveOrUpdate = () => {
      validate()
        .then(result => {
          if (result) {
            commonFieldData.value = Object.assign(commonFieldData.value, result);
            saveOrUpdateApi(commonFieldData.value)
              .then(res => {
                commonFieldData.value = Object.assign(commonFieldData.value, res);
                commonFieldDataId.value = commonFieldData.value.id + '';
                message.success(submitButtonTitlePrefix + '成功！');
                if (props.containerType === 'router') {
                  const { fullPath } = route; //获取当前路径
                  tabStore.closeTabByKey(fullPath, router).then(() => {
                    go('/settings/common-field-data/' + commonFieldDataId.value + '/edit', true);
                  });
                } else {
                  if (!props.savedOpen) {
                    ctx?.$emit('refresh', { update: true, containerType: props.containerType });
                  }
                  (ctx?.$refs['BASE_ENTITY'] as any).setFieldsValue(commonFieldData.value);
                }
              })
              .catch(error => {
                console.log('error', error);
                message.error(submitButtonTitlePrefix + '失败！');
              });
          } else {
            message.error('数据验证失败！');
          }
        })
        .catch(error => {
          console.log('error', error);
          message.error('数据验证失败！');
        });
    };
    //获得关联表属性。
    const pageConfig = reactive<any>({
      active: '0',
      operations: [
        {
          title: '关闭',
          type: 'default',
          theme: 'close',
          skipValidate: true,
          click: async () => {
            if (props.containerType === 'router') {
              const { fullPath } = route; //获取当前路径
              await tabStore.closeTabByKey(fullPath, router);
            } else {
              ctx?.$emit('cancel', { containerType: props.containerType, update: false });
            }
          },
        },
        {
          hide: () => {
            return !!commonFieldData.value.id;
          },
          type: 'primary',
          theme: 'save',
          click: saveOrUpdate,
        },
        {
          hide: () => {
            return !commonFieldData.value.id;
          },
          theme: 'update',
          type: 'primary',
          click: saveOrUpdate,
        },
      ],
    });
    const isEdit = computed(() => {
      return true;
    });
    const validate = async () => {
      let isValid = true;
      let result = {};
      var refKeys = Object.keys(ctx?.$refs as object);
      for (const refKey of refKeys) {
        const component: any = ctx?.$refs[refKey];
        if (['BASE_ENTITY', 'FormList'].includes(refKey)) {
          if (component && component.validate) {
            const validateResult = await component.validate();
            if (!validateResult) {
              isValid = false;
              break;
            } else {
              result = { ...result, ...validateResult };
            }
          }
        } else {
          if (component && component.validate) {
            const validateResult = await component.validate(true);
            if (!validateResult) {
              const { fullData } = component.getTableData();
              fullData.forEach(row => {
                if (typeof row.id === 'string' && row.id.startsWith('row_')) {
                  row.id = null;
                }
              });
              result[refKey] = fullData;
            } else {
              isValid = false;
            }
          }
        }
      }
      if (!isValid) {
        return false;
      } else {
        return result;
      }
    };
    const formGroup = computed(() => [
      {
        title: props.containerType === 'router' ? '通用字段数据' : null,
        operation: [],
        component: {
          name: 'a-form',
          props: {
            modelName: 'BASE_ENTITY',
            model: commonFieldData.value,
            labelWidth: '120px',
            fieldMapToTime: [],
            compact: true,
            alwaysShowLines: 1,
            schemas: formItemsConfig,
            // formItemsRender,
            size: 'default',
            disabled: false,
            showAdvancedButton: false,
            showResetButton: false,
            showSubmitButton: false,
            showActionButtonGroup: false,
            resetButtonOptions: {
              type: 'default',
              size: 'default',
              text: '关闭',
              preIcon: null,
            },
            actionColOptions: {
              span: 18,
            },
            submitButtonOptions: {
              type: 'primary',
              size: 'default',
              text: submitButtonTitlePrefix,
              preIcon: null,
            },
            resetFunc: () => {
              ctx?.$emit('cancel', { update: false, containerType: props.containerType });
            },
            submitFunc: saveOrUpdate,
          },
          on: {},
        },
      },
    ]);
    const formSlots = () => [];
    const renderChild = () => {
      const wrapperPros: any = {};
      if (!pageConfig?.canExpand) {
        wrapperPros.bordered = false;
        wrapperPros.size = 'small';
      }
      return formGroup.value.map(item => {
        var componentRef = item.component;
        if (componentRef && !(componentRef instanceof Array)) {
          if (componentRef.name === 'a-form') {
            if (pageConfig?.canExpand) {
              // @ts-ignore
              return h(CollapsePanel, {}, h(BasicForm, { ...componentRef.props, ref: componentRef.props.modelName }, formSlots));
            } else {
              // @ts-ignore
              return h(BasicForm, { ...componentRef.props, ref: componentRef.props.modelName }, formSlots);
            }
          } else {
            const component = resolveComponent(componentRef.name);
            return h(pageConfig?.canExpand ? CollapsePanel : Card, { ...wrapperPros }, () =>
              h(component, { ...componentRef.props, ref: componentRef.props.modelName }, () => []),
            );
          }
        } else if (componentRef && componentRef instanceof Array) {
          return h(pageConfig?.canExpand ? CollapsePanel : Card, { ...wrapperPros }, () =>
            h(Tabs, {}, () =>
              componentRef.map((child, index) => {
                const childComponent: Component = resolveComponent(child.name) as Component;
                return h(TabPane, { tab: child.title || index, key: index, disabled: child.disabled && child.disabled() }, () =>
                  child.disabled && child.disabled()
                    ? []
                    : [h(childComponent, { ...child.props, ref: child.props.modelName }, () => child.slots || {})],
                );
              }),
            ),
          );
        } else {
          return <div>无内容</div>;
        }
      });
    };
    const slots: any = {
      rightFooter: () => (
        <div>
          <Space>
            {pageConfig.operations.map((operation: any) => {
              const buttonSlots: any = {};
              if (operation.icon) {
                buttonSlots.icon = () => <Icon icon={operation.icon} />;
              }
              if (operation.text) {
                buttonSlots.default = () => operation.text;
              }
              const hideButton = isBoolean(operation.hide) ? operation.hide : isFunction(operation.hide) ? operation.hide() : false;
              switch (operation.theme) {
                case 'save':
                  if (!buttonSlots.icon) {
                    buttonSlots.icon = () => <Icon icon={'ant-design:save-outlined'} />;
                  }
                  if (!buttonSlots.default) {
                    buttonSlots.default = () => '保存';
                  }
                  return hideButton ? (
                    <span />
                  ) : (
                    <Button
                      {...{
                        type: operation.type || 'default',
                        onClick: () => {
                          validate().then(result => {
                            operation.click(result);
                          });
                        },
                      }}
                      v-slots={buttonSlots}
                    ></Button>
                  );
                case 'update':
                  if (!buttonSlots.icon) {
                    buttonSlots.icon = () => <Icon icon={'ant-design:check-outlined'} />;
                  }
                  if (!buttonSlots.default) {
                    buttonSlots.default = () => '更新';
                  }
                  return hideButton ? (
                    <span />
                  ) : (
                    <Button
                      {...{
                        type: operation.type || 'default',
                        onClick: () => {
                          validate().then(result => {
                            operation.click(result);
                          });
                        },
                      }}
                      v-slots={buttonSlots}
                    ></Button>
                  );
                default:
                  return hideButton ? (
                    <span />
                  ) : (
                    <Button
                      {...{
                        type: operation.type || 'default',
                        onClick: () => {
                          if (operation.skipValidate) {
                            operation.click();
                          } else {
                            validate().then(result => {
                              operation.click(result);
                            });
                          }
                        },
                      }}
                    >
                      {operation.title}
                    </Button>
                  );
              }
            })}
          </Space>
        </div>
      ),
      default: () => {
        if (pageConfig?.canExpand) {
          return (
            <div>
              <Collapse value={activeNames} onchange={handleChange} v-slots={{ default: () => renderChild() }} />
            </div>
          );
        } else {
          if (props.containerType === 'router') {
            return (
              <div>
                <Card
                  {...{
                    props: {
                      shadow: 'never',
                    },
                    bordered: false,
                    size: 'small',
                  }}
                  v-slots={{
                    title: () =>
                      h(Button, { preIcon: 'ant-design:form-outlined', type: 'link' }, () => [
                        commonFieldDataId.value ? '编辑通用字段数据' : '新增通用字段数据',
                      ]),
                    default: () => renderChild(),
                  }}
                ></Card>
              </div>
            );
          } else {
            return renderChild();
          }
        }
      },
    };
    return {
      // pageControl,
      commonFieldDataId,
      saveOrUpdate,
      formGroup,
      pageConfig,
      slots,
      commonFieldData,
    };
  },
  methods: {},
  render() {
    if (this.containerType === 'modal' || this.containerType === 'drawer') {
      // this.slots.actions = this.slots.rightFooter;
      delete this.slots.rightFooter;
      return <Card {...this.pageConfig} v-slots={this.slots} />;
    } else {
      return (
        <PageWrapper
          {...{
            props: {
              title: this.pageConfig?.title || '编辑',
            },
          }}
          v-slots={this.slots}
        />
      );
    }
  },
});
</script>
