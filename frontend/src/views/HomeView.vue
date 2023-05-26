<template>
  <div>
    <v-container>
      <v-row>
        <v-carousel hide-delimiters height="600px" :cycle="true" :interval="3000">
          <v-carousel-item
            v-for="(item, i) in items"
            :key="i"
            :src="item.src"
            reverse-transition="fade-transition"
            transition="fade-transition"
            eager
          >
            <v-container fill-height fluid pa-0 ma-0 pb-3>
              <v-layout fill-height align-end pb-4 mb-4>
                <v-flex xs12>
                  <v-card class="d-flex justify-end mb-6 transparent" flat tile dark>
                    <v-card-text>
                      <div class="text--primary">Final Projects</div>
                      <div class="text-h4">HA·PPY·HOU·SE</div>
                      <p class="text--primary">이상학 홍정원</p>
                      <div>
                        부동산 통계, 건축년도, 동별 매물, 년도별 매물, 월별 매물, 매매 가격 동향
                        안내.<br />
                        "부동산통계정보시스템 홈페이지에 오신 것을 환영합니다. "
                      </div>
                    </v-card-text>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-container>
          </v-carousel-item>
        </v-carousel>

        <v-col cols="12" md="4">
          <v-img src="../.././public/businessman1.jpg" class="mb-4"></v-img>
        </v-col>
        <v-col cols="12" md="4">
          <v-img src="../.././public/building1.jpg" class="mb-4"></v-img>
        </v-col>
        <v-col cols="12" md="4">
          <v-img src="../.././public/businessman2.jpg" class="mb-4"></v-img>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          src: require("../.././public/building5.jpg"),
        },
        {
          src: require("../.././public/building3.jpg"),
        },
        {
          src: require("../.././public/building2.jpg"),
        },
        {
          src: require("../.././public/building4.jpg"),
        },
      ],
    };
  },
};
</script>

<style scoped>
.v-card__text {
  color: rgb(238, 238, 238) !important;
}
</style>
