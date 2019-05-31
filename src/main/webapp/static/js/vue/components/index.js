/** 列表界面查询区操作按钮 **/
Vue.component('list-button', {
	inheritAttrs: false,
	props : ['but'],
	template : '<button v-on:click="event" class="layui-btn layui-btn-sm" type="button" v-html="but.icon+but.name"></button>',
	methods: {
		event: function () {
			      this.$emit('event')
			     }
		  }
});
