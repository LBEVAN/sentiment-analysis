<template id="button-toggle-group">
  <div class="btn-group">
    <button type="button" v-for="value in values" v-on:click="changeSelectValue(value)"
            :class="['btn', { 'btn-primary': selected === value, 'btn-default': selected !== value }]">{{ value.name }}
    </button>
  </div>
</template>

<script>
export default {
  template: '#button-toggle-group',
  props: ['values', 'default'],

  data () {
    return {
      selected: null
    }
  },

  ready: function () {
    this.selected = this.default
  },

  methods: {
    changeSelectValue: function (value) {
      this.selected = value
      this.$emit('inputSelected', this.selected)
    }
  }
}
</script>

<style>
  .btn:focus, .btn-primary:focus, .btn-default:focus {
    box-shadow: none;
  }
</style>
