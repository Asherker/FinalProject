<script>
</script>

<template>
    <div class="home-banner">
        <el-carousel height="500px">
            <el-carousel-item v-for="item in 5" :key="item.id">
                <img src=https://www.gu-global.com/tw/public/gutw/l1/2023/0707/top/02_t1_pc.jpg alt="">
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<style scoped lang='scss'>
.home-banner {
    width: 1240px;
    height: 500px;
    position: absolute;
    left: 0;
    top: 0;
    z-index: 98;

    img {
        width: 100%;
        height: 500px;
    }
}
</style>
