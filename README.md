# mudang2-server
무당이 위치제공 서비스 어플 서버에 관한 내용입니다.

## API
1. DB에서 busIdx별로 위도, 경도 데이터(lat, lon) 가져오기
2. DB에서 사람 수 데이터(headCount) 가져오기

# adminPage
무당이 관리자 대시보드 페이지에 관한 내용입니다.

## 주요 기능
### <무당이 device(gps)>
1. busIdx로 구분되는 각 라즈베리파이 - 내부 파일 구동 여부
- gpsToServer.py 파일: whlie문으로 무한루프 돌고 있는 것이 정상, 따라서 gpsToServer에 try catch문을 이용하여 작동여부 판단
-
-> 각 파일의 while문이 멈췄을 시(파일 작동이 멈추었을 시) 오류가 있다고 판단하여 에러문구를 print, 해당 에러문구를 adminPage 상에 표시
-> 대시보드에 표시하는 내용: 1. 무당이 device 2. 몇번째 device인지(busIdx) 3. 어떤 파일인지(파일명) 4. 상태(정상작동/에러-신호등처럼 표시)

2.
To-Do: 내부 파일 구조 및 작동시켜야하는 파일 순서

3. admin페이지에 데이터 사용량 표시 -> 확인 방법 물어보기(팀장이)

### <정류장 device(camera)>
1. 한개의 라즈베리파이 - 내부 파일 구동 여부
To-Do: 내부 파일 구조 및 작동시켜야하는 파일 순서

2. admin페이지에 데이터 사용량 표시

### <DB>
각 테이블(gps_device, camera_device를 일컫는다)의 createdAt 컬럼을 활용하여 각 모듈(gps모듈, camera모듈)에서 받아온 값이 라즈베리파이를 통해 서버 DB에 업데이트되었는지 여부를 확인
- 1차적으로는 API상으로 validation처리를 통해 검증, 오류가 있을 시 print되는 에러문구를 adminPage 상에 표시

## 기타 사항
IP 변경되었을 시 어떻게 관리?
