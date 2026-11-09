
<div align="center">

# 🔎 GitHub Hunter

### GitHub 개발 활동을 경험치로 시각화하는 대시보드

![Status](https://img.shields.io/badge/STATUS-IN_PROGRESS-F4B400?style=for-the-badge)
![Java](https://img.shields.io/badge/Java_21-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)

</div>

## 🎯 Overview

GitHub 커밋 데이터를 조회하여 개발 활동을 EXP와 레벨로 표현하는 개인 프로젝트입니다.

외부 REST API 연동과 데이터 가공, 대시보드 구현을 목표로 합니다.

## 🧩 Current Progress

- Java 21 / Spring Boot 프로젝트 구성
- 커밋 응답 DTO 설계
- Mustache 기반 `DEV HUNTER` 대시보드 화면 작성
- 커밋 수, 코드 변경량, EXP 통계 UI 구성
- 최근 커밋 목록 및 차트 UI 작성

## 🗓 Roadmap

- [x] 대시보드 화면 마크업
- [x] GitHub 커밋 응답용 DTO 정의
- [ ] GitHub REST API 서비스 연동
- [ ] 커밋 목록 및 상세 조회 API 완성
- [ ] 코드 변경량 기반 EXP 계산
- [ ] 일간·주간 통계 연결
- [ ] API 오류 처리 및 테스트 보강

## 🛠 Tech Stack

`Java 21` · `Spring Boot 4` · `Spring RestClient` · `Mustache` · `Gradle` · `HTML/CSS/JavaScript`

## 🧱 Architecture (Planned)

```text
GitHub REST API
       ↓
Spring RestClient
       ↓
Service (Commit / EXP)
       ↓
Controller
       ↓
Mustache Dashboard
```

## ▶️ Getting Started

```bash
git clone https://github.com/JunHH3/github-hunter.git
cd github-hunter
./gradlew bootRun
```

Windows에서는 `gradlew.bat bootRun`을 사용할 수 있습니다.

> 대시보드의 API 연동이 진행 중입니다. 현재 저장소에서 API 컨트롤러·서비스 구현은 확인되지 않아 전체 기능의 동작을 보장하지 않습니다.

[🔗 GitHub Repository](https://github.com/JunHH3/github-hunter)
