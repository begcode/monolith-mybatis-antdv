<!--
 * @Author: Vben
 * @Description: Arrow component with animation
-->
<template>
  <span :class="getClass">
    <Icon icon="ion:chevron-forward" :style="$attrs.iconStyle" />
  </span>
</template>
<script lang="ts" setup>
import { computed } from 'vue';
import Icon from '@/components/Icon/Icon.vue';
import { useDesign } from '@/hooks/web/useDesign';

const props = defineProps({
  /**
   * Arrow expand state
   */
  expand: { type: Boolean },
  /**
   * Arrow up by default
   */
  up: { type: Boolean },
  /**
   * Arrow down by default
   */
  down: { type: Boolean },
  /**
   * Cancel padding/margin for inline
   */
  inset: { type: Boolean },
});

const { prefixCls } = useDesign('basic-arrow');

// get component class
const getClass = computed(() => {
  const { expand, up, down, inset } = props;
  return [
    prefixCls,
    {
      [`${prefixCls}--active`]: expand,
      up,
      inset,
      down,
    },
  ];
});
</script>
<style scoped>
.vben-basic-arrow {
  display: inline-block;
  transform: rotate(0deg);
  transform-origin: center center;
  transition: all 0.3s ease 0.1s;
  cursor: pointer;
}
.vben-basic-arrow--active {
  transform: rotate(90deg);
}
.vben-basic-arrow.inset {
  line-height: 0px;
}
.vben-basic-arrow.up {
  transform: rotate(-90deg);
}
.vben-basic-arrow.down {
  transform: rotate(90deg);
}
.vben-basic-arrow.up.vben-basic-arrow--active {
  transform: rotate(90deg);
}
.vben-basic-arrow.down.vben-basic-arrow--active {
  transform: rotate(-90deg);
}
</style>
