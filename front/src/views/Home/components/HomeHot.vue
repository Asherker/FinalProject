<script setup>
import HomePanel from './HomePanel.vue'
</script>

<template>
    <HomePanel title="熱賣商品" sub-title="本季熱銷排行榜">
        <ul class="goods-list">
            <li>
                <RouterLink :to="'/detail'">
                    <img src="https://www.gu-global.com/tw/hmall/test/u0000000007950/main/first/1000/1.jpg" alt="">
                    <p class="name">平口內衣</p>
                    <p class="desc">
                        相當適合多層次造型的平口內衣
                    </p>
                </RouterLink>
            </li>
            <li>
                <RouterLink :to="'/detail'">
                    <img src="https://www.gu-global.com/tw/hmall/test/u0000000008349/main/first/1000/1.jpg" alt="">
                    <p class="name">網眼針織開領polo上衣</p>
                    <p class="desc">
                        寬鬆編織網眼圖樣的針織上衣
                    </p>
                </RouterLink>
            </li>
            <li>
                <RouterLink :to="'/detail'">
                    <img src="https://www.gu-global.com/tw/hmall/test/u0000000008354/main/first/1000/1.jpg" alt="">
                    <p class="name">bra-feel短版坦克背心</p>
                    <p class="desc">
                        美式削肩設計的內搭坦克背心
                    </p>
                </RouterLink>
            </li>
            <li>
                <RouterLink :to="'/detail'">
                    <img src="https://www.gu-global.com/tw/hmall/test/u0000000007865/main/first/1000/1.jpg" alt="">
                    <p class="name">輕型牛仔寬褲</p>
                    <p class="desc">
                        以輕磅牛仔材質製成的寬褲
                    </p>
                </RouterLink>
            </li>
        </ul>
    </HomePanel>
</template>

<style scoped lang='scss'>
.goods-list {
    display: flex;
    justify-content: space-between;
    height: 426px;

    li {
        width: 306px;
        height: 406px;
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
        }

        .desc {
            color: #999;
            font-size: 18px;
        }
    }
}
</style>
