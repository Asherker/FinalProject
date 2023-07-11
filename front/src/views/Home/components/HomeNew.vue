<script setup>
import HomePanel from './HomePanel.vue';
</script>

<template>
    <HomePanel title="最新上架" subTitle="熱騰騰剛上架的哦">
        <ul class="goods-list">
            <li>
                <RouterLink :to="'/detail'">
                    <img src="https://www.gu-global.com/tw/hmall/test/u0000000008561/main/first/1000/1.jpg" alt="" />
                    <p class="name">登山外套</p>
                    <p class="price">NT$1,090</p>
                </RouterLink>
            </li>
            <li>
                <RouterLink :to="'/detail'">
                    <img src="https://www.gu-global.com/tw/hmall/test/u0000000008448/main/first/1000/1.jpg" alt="" />
                    <p class="name">bra-feel短版繞頸細肩帶背心 </p>
                    <p class="price">NT$190</p>
                </RouterLink>
            </li>
            <li>
                <RouterLink :to="'/detail'">
                    <img src="https://www.gu-global.com/tw/hmall/test/u0000000008087/main/first/1000/1.jpg" alt="" />
                    <p class="name">亞麻混紡短版襯衫(5分袖)</p>
                    <p class="price">NT$390</p>
                </RouterLink>
            </li>
            <li>
                <RouterLink :to="'/detail'">
                    <img src="https://www.gu-global.com/tw/hmall/test/u0000000008608/main/first/1000/1.jpg" alt="" />
                    <p class="name">羅紋針織短版坦克背心</p>
                    <p class="price">NT$390</p>
                </RouterLink>
            </li>
        </ul>
    </HomePanel>
</template>

<style scoped lang='scss'>
.goods-list {
    display: flex;
    justify-content: space-between;
    height: 406px;

    li {
        width: 306px;
        height: 406px;

        // background: #f0f9f4;
        transition: all .5s;

        &:hover {
            transform: translate3d(0, -3px, 0);
            box-shadow: 0 3px 8px rgb(0 0 0 / 20%);
        }

        img {
            width: 306px;
            height: 306px;
        }

        p {
            font-size: 22px;
            padding-top: 12px;
            text-align: center;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
        }

        .price {
            color: $priceColor;
        }
    }
}
</style>

