//new Vue({
//    el: '#app',
//    data: {
//        urlField : '',
//        dissField  : '',
//        dissFieldValue   : '',
//        eMail : ''
//    }
//})
//


const app = new Vue({

    el: '#allert' ,
    data: {
        title: '',
        email: '',
        disscount: '',
        disscountValue: '',
        url: '',
        formInputs: []
    },
    methods: {
        addForm() {
            this.formInputs.push({
                email: this.email,
                disscount: this.disscount,
                disscountValueField: this.disscountValue,
                urlField: this.url,
                done: false
            });
            this.email = '';
            this.disscount = '';
            this.disscountValue = '';
            this.url  = '';
        },

        removeForm(field) {
            const fieldIndex = this.formInputs.indexOf(field);
            this.formInputs.splice(fieldIndex, 1);
        },
    }
});