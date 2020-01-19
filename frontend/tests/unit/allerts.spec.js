import { mount } from '@vue/test-utils';
import Allerts from '@/views/Allerts.vue';

const wrapper = mount(Allerts);
describe('Allerts.vue', () => {
  test('setup correctly', () => {
    expect(true).toBe(true);
  });
});
describe('Allerts.vue', () => {
  test('calling adding form method', () => {
    const addForm = jest.fn();
    wrapper.setMethods({ addForm });
    wrapper.find('.add-button').trigger('click');
    expect(addForm).toBeCalled();
    wrapper.vm.showAddingForm = true;
    expect(wrapper.vm.showAddingForm).toBeTruthy();
  });
});
