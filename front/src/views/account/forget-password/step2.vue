<template>
  <Form class="p-4 enter-x" :model="formData" :rules="getFormRules" ref="formRef">
    <FormItem name="login" class="enter-x">
      <Input size="large" v-model:value="formData.login" :placeholder="t('sys.login.userName')" disabled />
    </FormItem>

    <FormItem name="password" class="enter-x">
      <StrengthMeter size="large" v-model:value="formData.password" :placeholder="t('sys.login.password')" />
    </FormItem>

    <FormItem name="confirmPassword" class="enter-x">
      <InputPassword size="large" visibilityToggle v-model:value="formData.confirmPassword" :placeholder="t('sys.login.confirmPassword')" />
    </FormItem>

    <FormItem class="enter-x">
      <Button type="primary" size="large" block @click="handleNext"> 下一步 </Button>
      <Button size="large" block class="mt-4" @click="handlePrev"> 上一步 </Button>
    </FormItem>
  </Form>
</template>
<script lang="ts">
import { defineComponent, reactive, ref, toRaw } from 'vue';
import { Form, Input, Button } from 'ant-design-vue';
import { StrengthMeter } from '@begcode/components';
import { useI18n } from '@/hooks/web/useI18n';
import { useMessage } from '@/hooks/web/useMessage';
import { useFormRules, useFormValid } from '../login/useLogin';
import accountService from '@/api-service/account/account.service';

export default defineComponent({
  name: 'step2',
  components: {
    Button,
    Form,
    FormItem: Form.Item,
    InputPassword: Input.Password,
    Input,
    StrengthMeter,
  },
  props: {
    accountInfo: {
      type: Object,
      default: () => ({}),
    },
  },
  emits: ['prevStep', 'nextStep'],
  setup(props, { emit }) {
    const { t } = useI18n();
    const { createErrorModal } = useMessage();
    const { accountInfo } = props;
    const formRef = ref();
    const formData = reactive({
      login: accountInfo.obj.login || '',
      password: '',
      confirmPassword: '',
      resetKey: accountInfo.obj.resetKey,
    });
    const { getFormRules } = useFormRules(formData);
    const { validForm } = useFormValid(formRef);

    /**
     * 上一步
     */
    function handlePrev() {
      emit('prevStep', accountInfo.obj);
    }

    /**
     * 下一步
     */
    async function handleNext() {
      const data = await validForm();
      if (!data) return;
      const success = await accountService.resetPasswordFinish(
        toRaw({
          key: formData.resetKey,
          newPassword: data.password,
        }),
      );
      if (success) {
        //修改密码
        emit('nextStep', accountInfo.obj);
      } else {
        //错误提示
        createErrorModal({
          title: t('sys.api.errorTip'),
          content: t('sys.api.networkExceptionMsg'),
        });
      }
    }

    return {
      t,
      formRef,
      formData,
      getFormRules,
      handleNext,
      handlePrev,
    };
  },
});
</script>
