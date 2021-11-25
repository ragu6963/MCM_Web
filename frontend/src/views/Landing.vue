<template>
  <div class="landingpage">
    <div class="fullpage home1">
      <div class="content">
        <img class="main-img" src="../assets/main.png" alt="" />
        <span class="text" :class="{active: textActive}"></span>
        <span class="intro" :class="{active: isActive}">
          <div style="margin-bottom: 4rem;">
            <span>
              <span style="font-size: 4rem; color: #6595ed;">MCM</span> 은 "
              <span style="font-size: 3rem; color: #6595ed;">M</span>etaverse
              <span style="font-size: 3rem; color: #6595ed;">C</span>omputer
              <span style="font-size: 3rem; color: #6595ed;">M</span>useum"
              으로, </span
            ><br />
            <span
              >메타버스를 이용하여 시공간의 제약 없이 다양한 전시물의 관람 및
              체험이 가능하고</span
            ><br />
            <!-- <span></span><br> -->
            <span
              >다른 사용자들과의 소통이 가능하여 누구나 함께 즐길 수 있는
              메타버스 박물관입니다.</span
            >
          </div>
          <div
            class="animate__animated animate__bounce animate__repeat-2 animate__slow animate__delay-5s"
          >
            <span class="intro-next" @click="clickPage2R()">
              자세히 보기
            </span>
          </div>
        </span>
      </div>
    </div>
    <div class="fullpage home2">
      <span class="home3-title">Introduction</span>
      <div class="content">
        <div class="content-grid">
          <div class="card-box">
            <div class="card card1">
              <img class="card-img" src="../assets/computer.png" alt="" />
              <span class="card-title">메인존</span>
              <div class="card-content">
                <span
                  >컴퓨터의 역사와 발전 과정을<br />3D 모델을 통해 관람할 수
                  있습니다.</span
                >
              </div>
            </div>
          </div>
          <div class="card-box">
            <div class="card card2">
              <img class="card-img" src="../assets/gamepad.png" alt="" />
              <span class="card-title">체험존</span>
              <div class="card-content">
                <span
                  >3D 모델을 통해 게임기를 관람하고<br />게임을 체험할 수
                  있습니다.</span
                >
              </div>
            </div>
          </div>
          <div class="card-box">
            <div class="card card3">
              <img class="card-img" src="../assets/voice.png" alt="" />
              <span class="card-title">휴식존</span>
              <div class="card-content">
                <span
                  >다른 관람객들과 음성채팅을 통해<br />자유롭게 대화할 수
                  있습니다.</span
                >
              </div>
            </div>
          </div>
          <div class="card-box">
            <div class="card card4">
              <img class="card-img" src="../assets/video.png" alt="" />
              <span class="card-title">상영존</span>
              <div class="card-content">
                <span>저희 박물관의 UCC 영상을<br />시청할 수 있습니다.</span>
              </div>
            </div>
          </div>
        </div>
        <div class="left" @click="clickPage1()">
          <i
            class="fas fa-angle-double-left animate__animated animate__pulse animate__infinite"
          ></i>
          <span>이전 페이지</span>
        </div>
        <div class="right" @click="clickPage3R()">
          <i
            class="fas fa-angle-double-right animate__animated animate__pulse animate__infinite"
          ></i>
          <span>다음 페이지</span>
        </div>
      </div>
    </div>
    <div class="fullpage home3">
      <span class="home3-title">Developers</span>
      <div class="content">
        <div class="profile profile1">
          <div class="profile-img profile1-img">
            <img class="img1" src="../assets/profile/son.png" alt="" />
            <div class="profile1-content">
              <span style="color: #dc143c;">Back-end</span><br />
              <span style="color: #6495ed;">Unity</span>
            </div>
          </div>
          <span class="name">김선홍</span>
        </div>
        <div class="profile profile2">
          <div class="profile-img profile2-img">
            <img class="img2" src="../assets/profile/dad.png" alt="" />
            <div class="profile2-content">
              <span style="color: #32cd32;">Front-end</span><br />
              <span style="color: #6495ed;">Unity</span>
            </div>
          </div>
          <span class="name">정우영</span>
        </div>
        <div class="profile profile3">
          <div class="profile-img profile3-img">
            <img class="img3" src="../assets/profile/uncle.png" alt="" />
            <div class="profile3-content">
              <span style="color: #32cd32;">Front-end</span><br />
              <span style="color: #7b68ee;">3D Modeling</span>
            </div>
          </div>
          <span class="name">단석환</span>
        </div>
        <div class="profile profile4">
          <div class="profile-img profile4-img">
            <img class="img4" src="../assets/profile/aunt.png" alt="" />
            <div class="profile4-content">
              <span style="color: #7b68ee;">3D Modeling</span><br />
              <span style="color: #6495ed;">Unity</span>
            </div>
          </div>
          <span class="name">손현주</span>
        </div>
        <div class="profile profile5">
          <div class="profile-img profile5-img">
            <img class="img5" src="../assets/profile/graduation.png" alt="" />
            <div class="profile5-content">
              <span style="color: #dc143c;">Back-end</span><br />
              <span style="color: #7b68ee;">3D Modeling</span>
            </div>
          </div>
          <span class="name">안광식</span>
        </div>
        <div class="left" @click="clickPage2L()">
          <i
            class="fas fa-angle-double-left animate__animated animate__pulse animate__infinite"
          ></i>
          <span>이전 페이지</span>
        </div>
        <div class="right" @click="clickPage4R()">
          <i
            class="fas fa-angle-double-right animate__animated animate__pulse animate__infinite"
          ></i>
          <span>다음 페이지</span>
        </div>
      </div>
    </div>
    <div class="fullpage home4">
      <Unity :class="{open: start}" />
      <div class="unity">
        <span class="enterance" :class="{open: !start}">
          입장 전,<br />
          <span style="font-size: 2.5rem;">
            음성채팅에 사용할 마이크를 선택해 주세요.
          </span>
          <p style="text-align:right;font-size:0.7rem;">
            (마이크 목록이 나오지 않으시면 새로고침을 눌러주세요.)
          </p>
        </span>
        <div class="audio">
          <AudioSelector @unityStart="unityStart()" :class="{open: !start}" />
        </div>
        <div class="left" @click="clickPage3L()" :class="{open: !start}">
          <i
            class="fas fa-angle-double-left animate__animated animate__pulse animate__infinite"
          ></i>
          <span>이전 페이지</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Unity from "@/views/Unity.vue"
import AudioSelector from "@/views/AudioSelector.vue"

export default {
  components: {
    AudioSelector,
    Unity,
  },
  data() {
    return {
      isActive: true,
      textActive: false,
      i: null,
      content: null,
      text: null,
      inMove: false,
      hover: true,
      start: true,
      intervalFunc: null,
    }
  },
  methods: {
    showIntro() {
      let img = document.querySelector(".main-img")
      img.style.opacity = "0.4"
      img.style.transition = "opacity 5s"
      let intro = document.querySelector(".intro")
      intro.style.opacity = "1"
      intro.style.display = "initial"
      intro.style.flexDirection = "column"
      intro.style.transition = "opacity 3s display 3s"
      intro.classList.add(
        "animate__animated",
        "animate__fadeInRight",
        "animate__slower"
      )
      // this.isActive = false
      this.textActive = true
      // setTimeout(() => this.textActive = true, 200)
      clearInterval(this.intervalFunc)
    },

    nextStep() {
      let title = document.querySelector(".text")
      // title.classList.add("animate__animated", "animate__fadeOutLeft", "animate__slower")
      title.style.opacity = "0"
      title.style.transform = "translateX(-100%)"
      title.style.transition = "transform 3s, opacity 2s"

      setTimeout(() => this.showIntro(), 300)
    },

    typing() {
      if (this.i < this.content.length) {
        let txt = this.content[this.i]
        if (this.i === 0 || this.i === 10 || this.i === 19) {
          this.text.innerHTML += `<span class='initial'>${txt}</sapn>`
        } else {
          this.text.innerHTML += txt === "\n" ? "<br/>" : txt
        }
        this.i++
      } else {
        setTimeout(() => this.nextStep(), 300)
      }
    },

    clickPage1() {
      let home1 = document.querySelector(".home1")
      let home2 = document.querySelector(".home2")
      let home4 = document.querySelector(".home4")
      home1.style.display = "initial"
      home2.style.display = "none"
      home4.style.display = "none"
      home1.classList.add(
        "animate__animated",
        "animate__fadeInLeft",
        "animate__fast"
      )
    },

    clickPage2L() {
      let home1 = document.querySelector(".home1")
      home1.style.display = "none"
      let home3 = document.querySelector(".home3")
      home3.style.display = "none"
      let home2 = document.querySelector(".home2")
      home2.style.display = "initial"
      // home2.classList.add("animate__animated", "animate__fadeInLeft", "animate__fast")
      home2.classList.replace("animate__fadeInRight", "animate__fadeInLeft")
      home2.style.transition = "display 3s"
    },

    clickPage2R() {
      let home1 = document.querySelector(".home1")
      home1.style.display = "none"
      let home3 = document.querySelector(".home3")
      home3.style.display = "none"
      let home2 = document.querySelector(".home2")
      home2.style.display = "initial"
      home2.classList.add(
        "animate__animated",
        "animate__fadeInRight",
        "animate__fast"
      )
      home2.style.transition = "display 3s"
    },

    clickPage3L() {
      let home2 = document.querySelector(".home2")
      home2.style.display = "none"
      let home4 = document.querySelector(".home4")
      home4.style.display = "none"
      let home3 = document.querySelector(".home3")
      home3.style.display = "initial"
      home3.classList.replace("animate__fadeInRight", "animate__fadeInLeft")
      home3.style.transition = "display 3s"
    },

    clickPage3R() {
      let home2 = document.querySelector(".home2")
      home2.style.display = "none"
      let home4 = document.querySelector(".home4")
      home4.style.display = "none"
      let home3 = document.querySelector(".home3")
      home3.style.display = "initial"
      home3.classList.add(
        "animate__animated",
        "animate__fadeInRight",
        "animate__fast"
      )
      home3.style.transition = "display 3s"
    },

    clickPage4R() {
      let home3 = document.querySelector(".home3")
      home3.style.display = "none"
      let home4 = document.querySelector(".home4")
      home4.style.display = "initial"
      home4.classList.add(
        "animate__animated",
        "animate__fadeInRight",
        "animate__fast"
      )
      home4.style.transition = "display 3s"
    },

    unityStart(value) {
      this.start = value
    },
  },

  mounted() {
    this.content = "Metaverse\nComputer\nMuseum"
    this.text = document.querySelector(".text")
    this.i = 0
    this.intervalFunc = setInterval(() => this.typing(), 150)
  },
  destroyed() {
    clearInterval(this.intervalFunc)
  },
}
</script>

<style>
.landingpage {
  display: flex;
}
.fullpage.home1 {
  height: 100vh;
  width: 100vw;
}
.fullpage.home2,
.fullpage.home3,
.fullpage.home4 {
  height: 100vh;
  width: 100vw;
  display: none;
}
.main-img {
  position: absolute;
  top: 50%;
  left: 50%;
  height: 80vh;
  width: 80vw;
  transform: translate(-50%, -50%);
  border-radius: 5rem;
  opacity: 0;
}
.content {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.content-grid {
  display: grid;
  position: absolute;
  top: 20%;
  grid-template-columns: repeat(2, 1fr);
  width: 60%;
  height: 70%;
  justify-content: center;
  align-items: center;
}
.card-box {
  width: 30vw;
  height: 35vh;
  /* border: 1px solid orange; */
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
}
.card {
  width: 85%;
  height: 85%;
  border-radius: 5%;
  box-shadow: 4px 4px 8px #0000001a;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
}
.card1 {
  background: #f0ffff;
}
.card2 {
  background: #ffffe0;
}
.card3 {
  background: #f0fff0;
}
.card4 {
  background: #f5f5f5;
}
.card-img {
  width: 80%;
  height: 80%;
  max-width: 420px;
  max-height: 336px;
  border-radius: 5%;
  opacity: 0.7;
  z-index: 0;
}
.card-title {
  position: absolute;
  top: 50%;
  left: 50%;
  font-size: 40px;
  font-weight: 700;
  width: 80%;
  text-align: center;
  transform: translate(-50%, -50%);
}
.card-content {
  position: absolute;
  text-align: center;
  top: 50%;
  left: 50%;
  font-size: 19px;
  font-weight: 600;
  width: 80%;
  transform: translate(-50%, -50%);
  opacity: 0;
  /* line-height: 3rem; */
}
.card:hover {
  transform: scale(1.05);
  transition: 0.5s ease-in-out;
}
.card:hover .card-img {
  opacity: 0.2;
  transition: 0.5s ease-in-out;
}
.card:hover .card-title {
  top: 30%;
  transition: 0.5s ease-in-out;
}
.card:hover .card-content {
  top: 65%;
  opacity: 1;
  transition: 0.5s ease-in-out;
}
.home3-title {
  position: absolute;
  top: 8%;
  left: 50%;
  font-size: 5rem;
  font-weight: 700;
  transform: translate(-50%, -50%);
  color: #6595ed;
}
.profile {
  width: 12vw;
  height: 12vw;
  /* max-width: 200px;
  max-height: 200px; */
  text-align: center;
  position: absolute;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.profile1 {
  top: 45%;
  left: 20%;
}
.profile2 {
  top: 75%;
  left: 35%;
}
.profile3 {
  top: 45%;
  left: 50%;
}
.profile4 {
  top: 75%;
  left: 65%;
}
.profile5 {
  top: 45%;
  left: 80%;
}
.profile-img {
  border-radius: 100%;
  width: 12vw;
  height: 12vw;
  /* max-width: 200px;
  max-height: 200px; */
  border: 5px solid #6595ed;
  margin-bottom: 1rem;
  box-shadow: 4px 4px 8px #0000001a;
  position: relative;
}
.profile-img .img1,
.profile-img .img2,
.profile-img .img3,
.profile-img .img4,
.profile-img .img5 {
  width: 100%;
  height: 100%;
  border-radius: 100%;
}
.profile1-img:hover,
.profile2-img:hover,
.profile3-img:hover,
.profile4-img:hover,
.profile5-img:hover {
  transform: scale(1.1) rotateY(180deg);
  transition: 0.5s;
}
.profile-img:hover .img1,
.profile-img:hover .img2,
.profile-img:hover .img3,
.profile-img:hover .img4,
.profile-img:hover .img5 {
  opacity: 0;
  transition: 0.5s;
}
.profile1-content,
.profile2-content,
.profile3-content,
.profile4-content,
.profile5-content {
  width: 90%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%) scaleX(-1);
  font-size: 24px;
  font-weight: 500;
  opacity: 0;
}
.profile-img:hover .profile1-content,
.profile-img:hover .profile2-content,
.profile-img:hover .profile3-content,
.profile-img:hover .profile4-content,
.profile-img:hover .profile5-content {
  opacity: 1;
  transition: 1s ease-in-out;
}
.name {
  font-size: 2rem;
  font-weight: 600;
}
div.left {
  position: absolute;
  left: 5%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
div.left:hover {
  cursor: pointer;
  color: #6595ed;
  transition: 0.3s ease-in-out;
  transform: scale(1.1);
  /* transform: translateY(-50%); */
}
div.left i {
  font-size: 3.5rem;
}
div.left span {
  font-weight: 600;
}
div.right {
  position: absolute;
  right: 5%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
div.right:hover {
  cursor: pointer;
  color: #6595ed;
  transition: 0.3s ease-in-out;
  transform: scale(1.1);
}
div.right i {
  font-size: 3.5rem;
}
div.right span {
  font-weight: 600;
}
.text {
  font-size: 5rem;
  line-height: 8rem;
  font-weight: 500;
  z-index: 1;
}
.initial {
  font-size: 7rem;
  color: #6595ed;
  font-weight: 600;
}
.active {
  display: none;
}
.intro {
  display: none;
  font-size: 26px;
  font-weight: 600;
  opacity: 0;
  line-height: 6rem;
  width: 100%;
  padding: 15%;
}
.intro-next {
  margin-top: 4vh;
  font-size: 3.2rem;
}
.intro-next:hover {
  color: #6595ed;
  font-size: 3.5rem;
  transition: 0.3s ease-in-out;
  cursor: pointer;
}
.v-application .open {
  display: none !important;
}
.unity {
  display: flex;
  justify-content: center;
  align-items: center;
}
.enterance {
  position: absolute;
  top: 25%;
  left: 50%;
  font-size: 3rem;
  font-weight: 700;
  transform: translate(-50%, -50%);
  color: #6595ed;
}
</style>
