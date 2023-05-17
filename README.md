# jwp-lineMap-path

## 요구사항

### API 요구사항

- [x] 노선에 역 등록 API 신규 구현
- [x] 노선에 역 제거 API 신규 구현
- [x] 노선 조회 API 수정
    - 노선에 포함된 역을 순서대로 보여주도록 응답을 개선
- [x] 노선 목록 조회 API 수정
    - 노선에 포함된 역을 순서대로 보여주도록 응답을 개선
- [ ] 경로 조회 API 구현
    - 최단 거리 경로, 요금 정보 포함하여 응답
    - 요금 조회 기능 추가

### 프로그래밍 요구사항

- [ ] 데이터베이스 설정을 프로덕션과 테스트를 다르게 한다.
    - 프로덕션의 데이터베이스는 로컬에 저장될 수 있도록 설정
    - 테스트용 데이터베이스는 인메모리로 동작할 수 있도록 설정

### 도메인 요구사항

#### 구간

- 좌측 역
- 우측 역
- 거리
    - [x] `예외` 거리는 양의 정수여야 한다.
- [x] 구간은 동일한 두 역으로 이루어질 수 없다.
- [x] 구간에서 전달받은 구간만큼 뺀 구간을 구할 수 있다.
    - [x] 두 구간에 겹치는 영역이 없으면 차집합이 없으므로 빈 값을 반환한다.
    - [x] `예외` 전달받은 구간이 계산하는 구간보다 거리가 크거나 같을 수 없다. (거리 생성에서 예외 발생)
- [x] 구간과 전달받은 구간을 합친 구간을 구할 수 있다.
    - [x] 합치기 위한 구간과, 전달받은 구간이 어느 쪽인지 상관 없이 합친 구간을 구할 수 있다.
    - [x] 두 구간에 교점이 없으면 병합이 불가하므로 빈 값을 반환한다.

#### 개별 노선도 (노선 별 하행 기준 역 정보)

- 하행 기준 역 연결 그래프
    - [x] 순서가 없는 구간 목록을 전달받아 역으로 정점을, 연결 관계를 변으로, 거리를 너비로 표현하는 그래프를 생성한다.
        - [x] `예외` 중간에 연결이 끊기는 구간 목록으로 역 연결 그래프를 생성할 수 없다.
        - [x] `예외` 갈래길을 가지는 구간 목록으로 역 연결 그래프를 생성할 수 없다.
    - [x] 역과 방향을 전달받아 해당 역의 해당 방향에 대한 구간 정보를 반환할 수 있다.
    - [x] 전달받은 역을 정점으로 추가할 수 있다.
    - [x] 전달받은 구간을 변으로 추가할 수 있다.
    - [x] 전달받은 역에 해당하는 정점을 삭제할 수 있다.
- [x] 기준 역, 추가할 역, 거리, 방향을 전달받아 새 역을 등록할 수 있다.
    - [x] 노선에 역이 존재하지 않으면 기존 역, 추가할 역 모두 새로 등록한다.
        - [x] `예외` 두 역은 동일할 수 없다.
    - [x] 기준 역, 추가할 역, 거리, 방향을 전달받아 추가할 구간 정보를 생성한다.
    - [x] 기존 역 간 거리를 조정하여 새 역을 등록한다.
        - [x] `예외` 기준 역이 노선에 존재하지 않으면 등록할 수 없다.
        - [x] `예외` 추가할 역이 노선에 이미 존재하면 등록할 수 없다.
        - [x] 기존 구간으로부터 새 구간만큼 뺀 구간을 구해 그래프를 업데이트한다.
        - [x] 새 역을 기존 하행 종점 앞에 등록할 수 있다.
        - [x] 새 역을 기존 상행 종점 앞에 등록할 수 있다.
- [x] 역을 전달받아 삭제할 수 있다.
    - [x] 기존 역 간 거리를 조정하여 역을 삭제한다.
        - [x] 삭제할 역에 연결된 양 옆의 구간을 합친 구간을 구해 그래프를 업데이트한다.
    - [x] 하행 종점의 역을 삭제할 수 있다.
    - [x] 상행 종점의 역을 삭제할 수 있다.
- [x] 하행 기준으로 순서에 맞게 역의 목록을 반환할 수 있다.

#### 지하철 노선도

- 모든 노선의 역을 연결하는 그래프
    - [x] 순서가 없는 구간 목록을 노선 별로 전달받아 역으로 정점을, 연결 관계를 변으로, 거리를 너비로 표현하는 그래프를 생성한다.
        - [x] 각 변은 어떤 노선에 해당하는 연결 관계인지 알고 있다.
        - [x] 같은 출발지, 도착지를 가진 경로는 1개 이상일 수 있다.
        - [x] 갈래길이 존재할 수 있다.
- [ ] 출발역과 도착역을 전달받아 최단 거리 경로를 반환한다.
- [ ] 한 노선에서 경로 찾기 뿐만 아니라 여러 노선의 환승도 고려한다.

#### 이동 경로

- [ ] 해당 경로의 총 거리 정보를 계산해 반환한다.
- [ ] 해당 경로의 요금을 계산해 반환한다.

### 도메인 기능 조건

#### 노선 등록 조건

- [x] 노선에 역이 하나도 등록되지 않은 상황에서 최초 등록 시 두 역을 동시에 등록해야한다.
- [x] 하나의 역은 여러 노선에 등록이 될 수 있다.
- [x] 노선은 갈래길을 가질 수 없다.
- [x] 노선 가운데 역이 등록 될 경우 거리 정보를 고려해야한다.
- [x] 노선 가운데 역이 등록 될 경우 거리는 양의 정수라는 비즈니스 규칙을 지켜야한다.

#### 노선 역 제거

- [x] 노선에서 역을 제거할 경우 정상 동작을 위해 역을 재배치 되어야한다.
- [x] 노선에서 역이 제거될 경우 역과 역 사이의 거리도 재배정되어야한다.
- [x] 노선에 등록된 역이 2개 인 경우 하나의 역을 제거할 때 두 역이 모두 제거되어야한다.

## API 명세

### 역 등록

#### Request

- 역 등록은 기준역의 다음에 새로운 역을 추가합니다.
- 상행, 하행 방향을 지정해주어야 합니다.

```http request
POST /lines/{id} HTTP/1.1
{
    "sectionStations": {
        "baseStationId": 1,
        "nextStationId": 2,
        "distance": 2
    },
    "direction": "up"
}
```

#### Response

```http request
HTTP/1.1 201
```

### 역 제거

#### Request

```http request
DELETE /lines/{id}/{stationId} HTTP/1.1
```

#### Response

```http request
HTTP/1.1 204
```

### 경로 조회

```http request
GET /routes?sourceStationId={sourceStationId}&target={targetStationId} HTTP/1.1
```

## 코드리뷰 피드백 및 리팩터링 목록

## 1단계

- 피드백 외 리팩터링
    - [x] 도메인 객체 자체로 요구사항을 만족하도록 전면 리팩터링
        - [x] Subway 객체가 한 노선의 역 연결 정보를 위한 로직을 직접 수행하도록 변경
            - [x] 노선의 경로를 표현하는 도메인 객체 네이밍 수정 Subway -> LineRoute
            - [x] 역 연결을 표현하는 그래프 생성 로직 팩터리 클래스로 분리
            - [x] 역 연결을 표현하는 그래프 가독성, 네이밍 통일을 위해 커스텀 클래스로 변경
            - [x] 불필요한 Line 객체 참조 삭제
            - [x] 서비스 계층에서는 도메인과 DB를 연결만 하도록 변경
        - [x] 전면 리팩터링에 따라 사용하지 않는 메서드 삭제
        - [x] 객체 책임 분리
            - [x] Section 클래스로 구간 분할, 병합 책임 이동
            - [x] Distance 클래스로 거리 계산 책임 이동
    - [x] LineRoute 생성 로직 단순화
    - [x] DAO 메서드 변경, 변경에 따른 테스트코드 작성
    - [x] 서비스 검증 로직 추가
    - [x] 서비스 테스트코드 수정
    - [x] 서비스 트랜잭션 적용
    - [x] 테스트를 위한 메서드 삭제
    - [x] 테스트코드 중복코드 수정, 가독성 개선
    - [x] 커스텀 예외 정의, 예외 처리 기능 추가
    - [x] DTO 검증 로직 추가
        - not null 외의 검증은 도메인 책임으로 판단함
    - [x] 역 연결 그래프 일급 콜렉션으로 분리? 분리하는 의미가 없는 것 같아 진행하지 않음
    - [ ] 인수 테스트 잘못된 요청 값에 대한 응답 관련 테스트 추가
    - [ ] API 문서 자동화 도구 사용하기

- 피드백
    - [x] [LineRequest, LineResponse와 같이 외부 영역에서 정의되는 자료구조를 서비스에서 사용하면 어떤 문제가 있을까?](https://github.com/woowacourse/jwp-subway-path/pull/78#discussion_r1192853316)
    - [x] [객체 id로 동일성 비교 vs 모든 필드로 동일성 비교](https://github.com/woowacourse/jwp-subway-path/pull/78#discussion_r1192854360)
    - [x] [Optional 사용하여 존재하지 않는 값 안전하게 다루기](https://github.com/woowacourse/jwp-subway-path/pull/78#discussion_r1192856649)
    - [x] [예측하지 못한 예외 정보 콘솔 출력 대신 로그 사용하기](https://github.com/woowacourse/jwp-subway-path/pull/78#discussion_r1192856978)
    - [x] [여러 DB 수행이 존재하는 메서드 Transaction 처리](https://github.com/woowacourse/jwp-subway-path/pull/78#discussion_r1192857631)
    - [x] [데이터 초기화 Controller 대신 Spring 설정 사용하기(별도 bean 분리/sql 파일 사용)](https://github.com/woowacourse/jwp-subway-path/pull/78#discussion_r1192858741)
