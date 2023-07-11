import axios from 'axios';
export default {
  data() {
    return {
      bannerData: null,
    };
  },
  created() {
    axios.get('/public/DataBase/banner.json')
      .then(response => {
        this.bannerData = response.data;
      })
      .catch(error => {
        console.error(error);
      });
  },
  // 其他 Vue 組件選項和方法
};