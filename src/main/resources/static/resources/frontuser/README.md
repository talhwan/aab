
---

# AAB 프론트엔드 프로젝트

이 프로젝트는 **AAB** 플랫폼을 위한 웹 애플리케이션으로, SCSS, HTML, JavaScript를 사용하여 개발되었습니다.

### 배포된 사이트

현재 프로젝트는 Vercel에 배포되어 있습니다. 아래 링크를 통해 배포된 사이트를 확인할 수 있습니다:
https://aab-front.vercel.app/

---

## 프로젝트 구조

이 프로젝트는 **assets** 폴더에 이미지, 폰트, JavaScript, CSS 파일을 저장하며, **pages** 폴더에 각각의 HTML 페이지를 관리합니다. 또한, **scss** 폴더에 스타일 관련 파일들을 나누어 관리합니다. 아래는 디렉토리 구조입니다:

```csharp

assets/
    css/        - 컴파일된 CSS 파일들
    fonts/      - 폰트 파일들 (커스텀 및 외부 폰트)
    images/     - 웹사이트에 사용되는 이미지 파일들
    js/
        main.js - 웹사이트 상호작용을 처리하는 JavaScript 파일
    scss/
        _commingsoon.scss       - "Coming Soon" 섹션 스타일
        _footer.scss            - 푸터 스타일
        _header.scss            - 헤더 스타일
        _homepage.scss          - 홈페이지 스타일
        _mixins.scss            - 재사용성을 위한 SCSS 믹스인
        _modals.scss            - 모달 창 스타일
        _special-characters.scss- 특수 문자 스타일
        _tournaments.scss       - 토너먼트 섹션 스타일
        _variables.scss         - 스타일 일관성을 위한 SCSS 변수
        main.scss               - 모든 SCSS 파일을 포함하는 메인 스타일 파일

pages/
    contacts.html    - 연락처 페이지 (미완)
    event.html       - 이벤트 상세 페이지 (미완)
    homepage.html    - 홈페이지
    media.html       - 미디어 페이지 (미완)
    news.html        - 뉴스 페이지 (미완)
    tournaments.html - 토너먼트 목록 페이지

README.md            - 프로젝트 개요 및 설명
vercel.json          - Vercel 배포 설정 파일

```

---

## 페이지 설명

### 1. **홈페이지 (`homepage.html`)**

- 웹사이트의 메인 랜딩 페이지입니다.
- 주요 이벤트 배너, 추천 토너먼트, 빠른 접근 링크가 포함되어 있습니다.

### 2. **토너먼트 페이지 (`tournaments.html`)**

- 다가오는 토너먼트와 과거 토너먼트 목록을 표시합니다.
- 사용자 경험을 향상시키기 위한 스무스 스크롤 및 토글 기능이 포함되어 있습니다.

### 3. **연락처 페이지 (`contacts.html`)**

- 문의 정보가 제공되며, 사용자와의 상호작용을 위한 간단한 폼이 포함되어 있습니다.
- (현재 comming soon 페이지로 구현)

### 4. **이벤트 페이지 (`event.html`)**

- 특정 이벤트에 대한 자세한 정보를 제공합니다.
- 이미지, 날짜, 설명이 포함됩니다.
- (현재 comming soon 페이지로 구현)

### 5. **미디어 페이지 (`media.html`)**

- 토너먼트 및 이벤트와 관련된 이미지와 비디오를 보여주는 미디어 갤러리입니다.
- (현재 comming soon 페이지로 구현)

### 6. **뉴스 페이지 (`news.html`)**

- 최신 뉴스 및 업데이트 정보를 표시합니다.
- (현재 comming soon 페이지로 구현)

---

## 주요 기능

- **반응형 디자인**: 데스크탑과 모바일 모두 최적화된 화면을 제공합니다.
- **SCSS 스타일링**: 유지보수를 쉽게 하기 위해 SCSS 파일로 스타일이 나뉘어 있습니다.
- **동적 상호작용**: 스무스 스크롤, 토글 버튼, 모달 창을 통해 사용자 경험을 개선하였습니다.
- **모듈화된 구조**: 각 섹션에 대해 별도의 SCSS 파일이 있어 확장성과 커스터마이징이 용이합니다.

---

## 배포

이 프로젝트는 **Vercel**을 통해 배포되었습니다. `vercel.json` 파일을 통해 리디렉션 및 리라이트 등의 배포 설정이 적용되어 있습니다.

---

## 로컬 실행 방법

1. 이 저장소를 클론합니다:
    
    ```bash
    bash
    코드 복사
    git clone https://github.com/your-username/aab-front.git
    ```
    
2. 프로젝트 디렉토리로 이동합니다:
    
    ```bash
    bash
    코드 복사
    cd aab-front
    ```
    
    
3. `homepage.html` 파일을 브라우저에서 열어 로컬에서 사이트를 확인할 수 있습니다.

---


