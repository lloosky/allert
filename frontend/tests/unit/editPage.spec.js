import { mount } from '@vue/test-utils';
import EditPage from '@/components/EditPage.vue';

describe('EditPage.vue', () => {
  const wrapper = mount(EditPage);
  test('setup correctly', () => {
    expect(true).toBe(true);
  });
});
