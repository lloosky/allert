import { mount, createLocalVue } from '@vue/test-utils';
import VueRouter from 'vue-router';
import Nav from '@/components/Navigation.vue';

const localVue = createLocalVue();
localVue.use(VueRouter);
const router = new VueRouter();
const $route = {
  path: '/'
};

describe('Nav.vue', () => {
  const wrapper = mount(Nav, { localVue, router });
  test('setup correctly', () => {
    expect(true).toBe(true);
  });
});
