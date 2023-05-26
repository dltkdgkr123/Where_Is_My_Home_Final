<template>
  <v-container fluid :class="[computedMargin]">
    <v-row>
      <v-col cols="12" sm="8">
        <v-card class="d-flex mb-6" flat tile>
          <v-select
            v-model="selectedSido"
            :items="sidos"
            label="시도선택"
            @change="gugunList"
            dense
            solo
          ></v-select>
          <v-select
            v-model="selectedGugun"
            :items="guguns"
            label="구군선택"
            @change="dongList"
            dense
            solo
          ></v-select>
          <v-select v-model="selecetedDong" :items="dongs" label="읍면동선택" dense solo></v-select>
          <v-select v-model="selectedYear" :items="years" label="연도" dense solo></v-select>
          <v-select v-model="selectedMonth" :items="months" label="월" dense solo></v-select>
        </v-card>

        <v-text-field
          label="아파트명"
          v-model.lazy="searchWord"
          placeholder="아파트명을 입력하세요.."
          class="mt-n10"
          append-outer-icon="mdi-store-search"
          @click:append-outer="searchApt"
        >
        </v-text-field>
        <!-- <v-btn depressed color="primary" @click="searchApt"> search </v-btn> -->

        <v-card id="map" elevation=" rounded" width="1500px" height="700px"> </v-card>
      </v-col>

      <v-col cols="12" sm="4">
        <div>
          <v-card :class="[computedPadding]">
            <v-card>
              <div>
                <div id="roadview" style="width: 100%; height: 300px"></div>
                <div id="nocontent">
                  <v-img
                    src="../../.././public/nocontent.jpg"
                    style="width: 500px; height: 300px"
                  ></v-img>
                </div>
              </div>
            </v-card>
            <v-divider line></v-divider>
            <p class="font-weight-light">아파트 상세 정보</p>
            <v-divider line></v-divider>
            <div v-if="house">
              <p class="font-weight-light my-0">아파트 이름 : {{ choosedApt.aptName }}</p>
              <p class="font-weight-light my-0">아파트 층 : {{ house.floor }}층</p>
              <p class="font-weight-light my-0">아파트 면적 : {{ house.area }}㎡</p>
              <p class="font-weight-light my-0">
                아파트 주소 : {{ house.roadName }} {{ house.roadNameBonbun }}
              </p>
              <p class="font-weight-light my-0">아파트 건축년도 : {{ house.buildYear }}년</p>
              <p class="font-weight-light my-0">아파트 매매가격 : {{ choosedApt.price }}만원</p>
            </div>
            <div v-else>
              <p>아파트를 선택해주세요</p>
            </div>
          </v-card>
        </div>
        <template>
          <v-data-table
            :headers="headers"
            :items="houses"
            :items-per-page="5"
            class="elevation-1 mt-3"
            @click:row="goDetail"
          ></v-data-table>
        </template>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HomeList",
  data() {
    return {
      //맵 관련
      kakao: null,
      map: null,
      roadview: null,
      roadviewClient: null,
      roadviewOned: false,
      roadviewContainer: null,
      nocontent: null,
      markers: [],
      repreMarker: null,

      //아파트 관련
      // 선택된 지역코드가 담길 변수
      selectedSido: null,
      selectedGugun: null,
      selecetedDong: null,
      selectedYear: null,
      selectedMonth: null,
      // 검색어, 년월
      searchWord: "",
      years: ["2015", "2016", "2017", "2018", "2019", "2020", "2021"],
      months: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],

      //아파트 테이블 관련
      headers: [
        {
          text: "아파트이름",
          align: "start",
          sortable: false,
          value: "aptName",
        },

        { text: "매매년", value: "dealYear" },
        { text: "매매월", value: "dealMonth" },
        { text: "매매가격(만원)", value: "price" },
      ],

      //아파트 상세정보 관련
      choosedApt: {},

      //마진 관련
      marginDirection: "y",
      marginSize: "10",
      //패딩 관련
      paddingDirection: "a",
      paddingSize: "2",
    };
  },

  watch: {
    roadviewOned() {
      if (this.roadviewOned) {
        this.roadviewContainer.style.display = "block";
        this.nocontent.style.display = "none";
      } else {
        this.roadviewContainer.style.display = "none";
        this.nocontent.style.display = "block";
      }
    },
  },

  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses", "house"]),

    computedPadding() {
      return `p${this.paddingDirection}-${this.paddingSize}`;
    },
    computedMargin() {
      return `m${this.marginDirection}-${this.marginSize}`;
    },
    customMargin() {
      return (size) => {
        return `m${this.marginDirection}-${size}`;
      };
    },
  },

  created() {
    // 모든 선택리스트 초기화하고 getSido 호출
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },

  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList", "getHouseDetail"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
      "CLEAR_HOUSE",
    ]),
    // v-model selectedSido가 @change될 때 호출
    gugunList() {
      // 시도를 변경할 때, 구군에 여러 지역이 쌓이는 것을 방지하기 위해 clear하고 동작
      // 구군 리스트를 비우고 선택되지 않았다고 표시
      this.CLEAR_GUGUN_LIST();
      this.selectedGugun = null;
      this.CLEAR_DONG_LIST();
      this.selecetedDong = null;
      // if(0, null)이 아니면 동작 => 선택하세요 == null이므로 시도가 선택되었을 때만 동작
      if (this.selectedSido) this.getGugun(this.selectedSido);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.selecetedDong = null;
      if (this.selectedGugun) this.getDong(this.selectedGugun);
    },
    async searchApt() {
      if (!this.selectedSido) {
        alert("시도를 선택하세요.");
        return;
      }
      if (!this.selectedGugun) {
        alert("구군을 선택하세요.");
        return;
      }
      if (!this.selecetedDong) {
        alert("동읍면을 선택하세요.");
        return;
      }
      const params = {
        regCode: this.selecetedDong,
        searchWord: this.searchWord == "" ? "none" : this.searchWord,
        deal_y: this.selectedYear == null ? "none" : this.selectedYear,
        deal_m: this.selectedMonth == null ? "none" : this.selectedMonth,
      };

      await this.getHouseList(params);

      this.clearMarkerInMap();
      this.markInMap();

      // 조회 결과가 1개 이상인 경우에만 동작
      if (this.houses.length != 0) {
        this.setRepreMarker(this.houses[0].lat, this.houses[0].lng);
        this.moveToRepreMarkerFast();
        this.moveRoadView();
      }

      alert("조회되었습니다.");
    },
    //맵관련
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(36.35528900964476, 127.29954884757659),
        level: 5,
      };

      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },

    initRoadView() {
      // 2d 다중로딩시 시간을 감소시켜준다고 설정하라는 주의메세지가 콘솔창에 뜸 => canvas 라이브러리 임포트 못해서 보류
      // canvas.getContext("webgl", {
      //   antialias: false,
      //   depth: false,
      // });
      this.nocontent = document.getElementById("nocontent");
      const roadviewContainer = document.getElementById("roadview");
      const roadview = new kakao.maps.Roadview(roadviewContainer);
      const roadviewClient = new kakao.maps.RoadviewClient();

      const position = new kakao.maps.LatLng(36.35528900964476, 127.29954884757659);

      roadviewClient.getNearestPanoId(position, 50, (panoId) => {
        if (panoId) {
          roadview.setPanoId(panoId, position);
          this.roadviewOned = true;
        } else {
          this.roadviewOned = false;
        }
      });

      // 로드뷰 좌표 변경시마다 객체를 새로 생성하지 않고 좌표만 옮기기 위해 전역data저장
      this.roadview = roadview;
      this.roadviewClient = roadviewClient;
      this.roadviewContainer = roadviewContainer;
    },

    setRepreMarker(lat, lng) {
      this.repreMarker = new kakao.maps.LatLng(parseFloat(lat), parseFloat(lng));
    },

    markInMap() {
      // 마커 이미지의 이미지 주소입니다
      const imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      const houses = this.houses;

      for (var i = 0; i < houses.length; i++) {
        const house = houses[i];

        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        this.markers.push(
          new kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: new kakao.maps.LatLng(house.lat, house.lng), // 마커를 표시할 위치
            title: house.aptName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
          })
        );
      }
    },

    clearMarkerInMap() {
      // 마커가 이미 찍혀있을 때만 이전 마커들을 삭제 => 두번 이상 조회시 중복마킹 방지
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
    },

    moveToRepreMarkerFast() {
      this.map.setCenter(this.repreMarker);
    },

    moveToRepreMarkerSlowly() {
      this.map.panTo(this.repreMarker);
    },

    moveRoadView() {
      //일반 메소드에선 this.roadviewClient와 this.roadview의
      // this가 서로 다르므로 에로우펑션으로 처리해야 함
      this.roadviewClient.getNearestPanoId(this.repreMarker, 50, (panoId) => {
        if (panoId) {
          this.roadview.setPanoId(panoId, this.repreMarker);
          this.roadviewOned = true;
        } else {
          this.roadviewOned = false;
        }
      });
    },

    async goDetail(event, { item }) {
      this.choosedApt = item;
      await this.getHouseDetail(this.choosedApt.no);

      this.setRepreMarker(this.choosedApt.lat, this.choosedApt.lng);
      // 빠르게 이동
      // this.moveToRepreMarkerFast();
      // 천천히 이동
      this.moveToRepreMarkerSlowly();
      this.moveRoadView();
    },
  },
  mounted() {
    // 카카오맵이 이미 있으면 맵을 초기화
    if (window.kakao && window.kakao.maps) {
      this.initMap();
      this.initRoadView();
    }
    // 없으면 키를 이용해 초기 설정으로 맵 추가
    else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => {
        kakao.maps.load(this.initMap);
        kakao.maps.load(this.initRoadView);
      };
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAOMAP_KEY}&autoload=false`;
      document.head.appendChild(script);
    }
  },
};
</script>
