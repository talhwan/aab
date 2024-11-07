document.addEventListener('DOMContentLoaded', function () {
  // Scroll Up/Down 버튼 기능 구현
  const scrollUpBtn = document.getElementById('home-scroll-up');
  const scrollDownBtn = document.getElementById('home-scroll-down');

  if (scrollUpBtn) {
    scrollUpBtn.addEventListener('click', function () {
      window.scrollTo({
        top: 0,
        behavior: 'smooth',
      });
    });
  }

  if (scrollDownBtn) {
    scrollDownBtn.addEventListener('click', function () {
      window.scrollTo({
        top: document.body.scrollHeight,
        behavior: 'smooth',
      });
    });
  }

  // 뉴스 카드 넘김 기능 구현
  const prevBtn = document.querySelector('.prev-btn');
  const nextBtn = document.querySelector('.next-btn');
  const carousel = document.querySelector('.carousel');
  const cardWidth = document.querySelector('.news-card').offsetWidth; // 카드 한 개의 너비 계산
  let scrollAmount = 0;

  nextBtn.addEventListener('click', () => {
    const maxScrollLeft = carousel.scrollWidth - carousel.clientWidth;
    if (scrollAmount < maxScrollLeft) {
      scrollAmount += cardWidth; // 카드 한 개 너비만큼 이동
      carousel.scrollTo({
        left: scrollAmount,
        behavior: 'smooth',
      });
    }
  });

  prevBtn.addEventListener('click', () => {
    if (scrollAmount > 0) {
      scrollAmount -= cardWidth; // 카드 한 개 너비만큼 이동
      carousel.scrollTo({
        left: scrollAmount,
        behavior: 'smooth',
      });
    }
  });
});


//토너먼트페이지

document.addEventListener('DOMContentLoaded', function () {
  const tabLinks = document.querySelectorAll('.tab-link');
  const tabPanels = document.querySelectorAll('.tab-panel');

  tabLinks.forEach(link => {
    link.addEventListener('click', function (event) {
      event.preventDefault();

      // 모든 탭을 비활성화
      tabLinks.forEach(link => link.classList.remove('active'));
      tabPanels.forEach(panel => panel.classList.remove('active'));

      // 클릭된 탭을 활성화
      this.classList.add('active');
      const targetPanel = document.getElementById(this.dataset.tab);
      targetPanel.classList.add('active');
    });
  });
});


//토너먼트페이지 2-2 gallery 카드 넘김 기능 구현
window.addEventListener('load', function () {
  const prevB = document.querySelector('.prevB');
  const nextB = document.querySelector('.nextB');
  const caro = document.querySelector('.caro');
  const cardWidth = document.querySelector('.gallery-card').offsetWidth; // 카드 한 개의 너비 계산
  console.log(cardWidth);

  
    let scrollAmount = 0;

    nextB.addEventListener('click', () => {
      const maxScrollLeft = caro.scrollWidth - caro.clientWidth;
      if (scrollAmount < maxScrollLeft) {
        scrollAmount += cardWidth; // 카드 한 개 너비만큼 이동
        caro.scrollTo({
          left: scrollAmount,
          behavior: 'smooth',
        });
      }
    });

    prevB.addEventListener('click', () => {
      if (scrollAmount > 0) {
        scrollAmount -= cardWidth; // 카드 한 개 너비만큼 이동
        caro.scrollTo({
          left: scrollAmount,
          behavior: 'smooth',
        });
      }
    });

});

/* 모바일 햄버거 버튼 기능 */
document.addEventListener('DOMContentLoaded', function () {
  const menuToggleBtn = document.querySelector('.menu-toggle');
  const navContainer = document.querySelector('.nav-container');

  // 메뉴 열기/닫기 토글 기능 추가
  menuToggleBtn.addEventListener('click', function () {
    navContainer.classList.toggle('active'); // 클래스 토글로 메뉴를 보이거나 숨김
    menuToggleBtn.classList.toggle('active'); // 햄버거 아이콘 모양도 변경
  });
});



/* 모달 관련 */
document.addEventListener('DOMContentLoaded', function () {
  const loginModal = document.getElementById('login-modal');
  const kakaoModal = document.getElementById('kakao-modal');
  const openLoginPopupBtn = document.querySelector('.btn-signin');
  const openKakaoPopupBtn = document.querySelector('.social-btn img');
  const closeLoginBtn = document.querySelector('.login-close-btn');
  const openSubscribeImage = document.querySelector('.footer-image-container img'); // 이미지 선택
  const openBannerButton = document.querySelector('.banner-button'); // 배너 버튼 선택
  const openLoginBtn = document.querySelector('.btn-login'); // Log In 버튼 선택

  // 팝업 열기 - 로그인 모달 (Sign In 버튼 클릭 시)
  if (openLoginPopupBtn) {
    openLoginPopupBtn.addEventListener('click', function (event) {
      event.preventDefault();
      loginModal.style.display = 'flex';
      kakaoModal.style.display = 'none'; // 카카오 모달을 숨김
    });
  }

  // 팝업 열기 - 카카오 회원가입 모달
  if (openKakaoPopupBtn) {
    openKakaoPopupBtn.addEventListener('click', function (event) {
      event.preventDefault();
      kakaoModal.style.display = 'flex';
      loginModal.style.display = 'none'; // 로그인 모달을 숨김
    });
  }

  // 팝업 열기 - 로그인 모달 (구독 이미지 클릭 시)
  if (openSubscribeImage) {
    openSubscribeImage.addEventListener('click', function (event) {
      event.preventDefault(); // 기본 이미지 클릭 동작 방지
      loginModal.style.display = 'flex'; // 로그인 모달을 표시
      kakaoModal.style.display = 'none'; // 카카오 모달을 숨김
    });
  }

  // 팝업 열기 - 로그인 모달 (배너 버튼 클릭 시)
  if (openBannerButton) {
    openBannerButton.addEventListener('click', function (event) {
      event.preventDefault();
      loginModal.style.display = 'flex'; // 로그인 모달을 표시
      kakaoModal.style.display = 'none'; // 카카오 모달을 숨김
    });
  }

  // 팝업 열기 - 로그인 모달 (Log In 버튼 클릭 시)
  if (openLoginBtn) {
    openLoginBtn.addEventListener('click', function (event) {
      event.preventDefault();
      loginModal.style.display = 'flex'; // 로그인 모달을 표시
      kakaoModal.style.display = 'none'; // 카카오 모달을 숨김
    });
  }

  // 팝업 닫기
  if (closeLoginBtn) {
    closeLoginBtn.addEventListener('click', function () {
      loginModal.style.display = 'none';
      kakaoModal.style.display = 'none';
    });
  }

  // 배경 클릭 시 팝업 닫기 - 로그인 모달
  loginModal.addEventListener('click', function (event) {
    if (event.target === loginModal) {
      loginModal.style.display = 'none';
    }
  });

  // 배경 클릭 시 팝업 닫기 - 카카오 모달
  kakaoModal.addEventListener('click', function (event) {
    if (event.target === kakaoModal) {
      kakaoModal.style.display = 'none';
    }
  });
});



/* 스크롤 관련 */
document.addEventListener("DOMContentLoaded", function () {
  const upcomingTournamentListContainer = document.getElementById('upcomingTournamentListContainer');
  const previousTournamentListContainer = document.getElementById('previousTournamentListContainer');
  
  const upcomingToggleDownButton = document.getElementById("upcomingToggleDownButton");
  const previousToggleDownButton = document.getElementById("previousToggleDownButton");

  let autoScrollInterval;
  let isAutoScrolling = true;

  // 자동 스크롤 함수
  function startAutoScroll(container) {
    return setInterval(() => {
      container.scrollBy({
        top: 300, // 한번에 스크롤할 픽셀 양
        behavior: 'smooth'
      });

      // 스크롤이 맨 끝에 도달했는지 확인하고, 도달하면 다시 위로 이동
      if (container.scrollTop + container.clientHeight >= container.scrollHeight) {
        container.scrollTo({
          top: 0,
          behavior: 'smooth'
        });
      }
    }, 2000); // 2초마다 스크롤
  }

  // 자동 스크롤 시작
  let upcomingAutoScroll = startAutoScroll(upcomingTournamentListContainer);
  let previousAutoScroll = startAutoScroll(previousTournamentListContainer);

  // 사용자가 스크롤할 때 자동 스크롤을 중단하는 이벤트 리스너
  function addManualScrollListener(container, autoScrollVariable) {
    container.addEventListener('scroll', function () {
      clearInterval(autoScrollVariable);
      isAutoScrolling = false;

      // 사용자가 스크롤을 중단하면 일정 시간 후 자동 스크롤 재개
      setTimeout(() => {
        if (!isAutoScrolling) {
          autoScrollVariable = startAutoScroll(container);
          isAutoScrolling = true;
        }
      }, 3000); // 3초 후 자동 스크롤 재개
    });
  }

  // 수동 스크롤 이벤트 리스너 추가
  addManualScrollListener(upcomingTournamentListContainer, upcomingAutoScroll);
  addManualScrollListener(previousTournamentListContainer, previousAutoScroll);

  // 수동으로 아래로 스크롤 버튼 클릭 시
  function addButtonScrollListener(button, container, autoScrollVariable) {
    button.addEventListener("click", function () {
      container.scrollBy({
        top: 300, // 수동 스크롤할 양
        behavior: 'smooth'
      });

      // 버튼 클릭 시에도 자동 스크롤 중단
      clearInterval(autoScrollVariable);
      isAutoScrolling = false;

      // 3초 후 자동 스크롤 재개
      setTimeout(() => {
        if (!isAutoScrolling) {
          autoScrollVariable = startAutoScroll(container);
          isAutoScrolling = true;
        }
      }, 3000);
    });
  }

  // 버튼 이벤트 리스너 추가
  addButtonScrollListener(upcomingToggleDownButton, upcomingTournamentListContainer, upcomingAutoScroll);
  addButtonScrollListener(previousToggleDownButton, previousTournamentListContainer, previousAutoScroll);
});
