// 封裝購物車模組

import { defineStore } from 'pinia'
import { ref } from 'vue'


export const useCartStore = defineStore('cart', () => {
  // 1. 定義state - cartList
  const cartList = ref([])
  // 2. 定義action - addCart
  const addCart = (goods) => {
    console.log('添加', goods)
    // 添加購物車操作
    // 已添加過 - count + 1
    // 沒有添加過 - 直接push
    // 思路：通過匹配傳遞過來的商品物件中的skuId能不能在cartList中找到，找到了就是添加過
    const item = cartList.value.find((item) => goods.skuId === item.skuId)
    if (item) {
      // 找到了
      item.count++
    } else {
      // 沒找到
      cartList.value.push(goods)
    }
  }
  return {
    cartList,
    addCart
  }
}, {
  persist: true,
})
