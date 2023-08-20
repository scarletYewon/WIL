# ❤️ WIL : What I Loved ❤️

간단한 로그인과 게시판 기능을 구현한 앱입니다.
# Member
- 류연주 : PM,Develop
- 이예원 : Develop
- 권경운 : Develop
- 우재훈 : Develop
- 이도연 : Develop

# Environment

- Kotlin 1.10.1
- Android Studio
- Firebase

# SDK Version

- SDK version : **Android 13**
- compileSdk : 33
- minSdk : 31
- targetSdk : 33

# D**emonstration Video Link**

[https://youtu.be/cx0ZbiT-R-o](https://youtu.be/cx0ZbiT-R-o)

# WireFrame


# Description

## 로그인

- 아이디/비밀번호 하나라도 입력되지 않으면 토스트 메세지 띄우기
- 비밀번호 입력 시, 보안 유지를 위해 ***로 보이도록 설정
- 아이디는 이메일 형식으로 입력하지 않으면 이메일 확인 요청 토스트 메세지
- 비밀번호는 영문,숫자 조합으로 8~20자 까지만 입력 가능 (아닐 경우 비밀번호 확인 요청 토스트 메세지)
- SIGNUP 버튼 클릭시 왼쪽에서 화면이 슬라이드되어 나오도록 Animation구현
- 로그인 할 때 firebase에 저장된 아이디, 비밀번호 값과 비교하여 일치하면 로그인 성공

## 회원가입

- SIGNUP 버튼 클릭 시 다이얼로그를 이용해 이용약관 팝업 띄우기
- 이용약관 동의버튼을 클릭해야지만 회원가입 및 계정생성 완료
- 회원가입 정보가 하나라도 입력되지 않으면 회원가입 불가능
- 회원가입 완성하면 이름, 아이디, 비밀번호 등 작성했던 정보들을 firbase에 저장

## 포스트 작성

- 게시 버튼 클릭 시 topic의 리스트를 추가하며 Topic 페이지로 이동
- 제목과 내용 중 하나라도 입력되지 않으면 페이지 넘어가지 않고, 입력 요구 토스팅 띄우기
- 입력한 제목/ 내용 값을 Topic 리스트에 전달

## 댓글 추가

- 댓글을 입력하지 않고 send 버튼 클릭 시 입력 요구 토스팅 띄우기
- 따봉 버튼 클릭 시 좋아요 기능 구현

## 마이페이지 작성

- 영어버전 제작
- 동그란 이미지 뷰 제작
- 버튼 이동시 정보 수정 페이지 이동, 및 제작
- DB(firebase realtime database)에 저장된 정보를 mypage에 데이터 불러오기
- 프로필 편집 시 변경한 내용을 DB에 반영하여 데이터 값 변경

## 메인 화면

- 각각의 게시판을 나눠서 똑같은 xml 화면을 재활용 하여 게시판 별로 구분
- 게시판 별 버튼을 통해 각각의 클래스 화면으로 이동

## 게시판 주제

- mypage에 대한 이름 데이터 깂 가져와 보여주기
- 하트모양 클릭 시 좋아요 기능 구현
- NEW버튼 클릭 시 새로운 리스트를 추가할 수 있는 화면으로 이동
- 리스트 항목 클릭 시 One post로 이동
- list view함수를 통해 항목 추가
- 새로운 리스트 생성 시 1~100까지의 랜덤한 조회수/ 댓글/ 좋아요 난수 생성

# 주요코드 및 기능

## 로그인

```kotlin
// 회원가입 화면으로 이동 시 왼쪽에서 슬라이드로 화면 띄우는 애니메이션 추가
val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            //애니메이션 추가
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
```

```kotlin
// 아이디(이메일 형식으로만 입력 가능), 비밀번호(숫자,영문 조합 8~20자까지만 가능) 기능 구현
if (!Pattern.matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*\$", inputId)) {
                    Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
                }
                if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}\$", inputPassword)){
                    Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                } else{
                    signinEmail(inputId,inputPassword)
                }
```

## 회원가입

```kotlin
//이용약관 다이얼로그 띄우기
//동의 버튼을 클릭해야지만 회원가입 완료
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("이용약관")
                        .setMessage("1. 개인정보의 수집‧이용 목적\n" +
                                "WIL에 대한 이용문의 민원에 대한 처리\n" +
                                "\n" +
                                "2. 수집하는 개인정보의 항목\n" +
                                "작성자 이름, 휴대폰 번호\n" +
                                "\n" +
                                "3. 개인정보 수집에 대한 거부 권리 및 그에 따른 제한사항\n" +
                                "이용자는 WIL의 개인정보 수집에 대한 동의 요청을 거부할 권리가 있으며 거부할 경우 WIL 이용문의 서비스 이용에 대한 제한을 받을 수 있습니다.")
                        .setPositiveButton("동의",
                            DialogInterface.OnClickListener { dialog, id ->
                                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                                createAccount(inputName,inputId,inputPassword,inputMbti,inputAddress,inputInterest,accept = true)
                            })
                        .setNegativeButton("취소",
                            DialogInterface.OnClickListener { dialog, id ->
                                Toast.makeText(this, "이용약관에 동의하지 않으면 회원가입이 불가합니다.", Toast.LENGTH_SHORT).show()
                            })
                    builder.show()
```

## 포스트 작성

```jsx
// 제목과 내용 둘 중 하나라도 입력 안되면 토스트 메시지 띄우기
        post.setOnClickListener {

            val inputTitle = title.text.toString().trim()
            val inputMemo = memo.text.toString().trim()

            if (title.text.toString().trim().isEmpty() || memo.text.toString().trim().isEmpty()
            ) {
                Toast.makeText(this, "제목과 내용 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
```

```jsx
// 버튼 클릭 시 Test 화면으로 이동 (Topic으로 비꾸세요.)
         else {
                //Topic1 class 로 데이터 전달 위한 변수명 설정
                val title_test = findViewById<EditText>(R.id.et_title)
                val strData_title = title_test.text.toString()
                val strData_memo = memo.text.toString()

                // 리스트 항목 추가.
                val intent = Intent(this, Topic1::class.java)
                // test class 로 key 값 전달
                intent.putExtra("title", strData_title)
                intent.putExtra("memo", strData_memo)
                startActivity(intent)
```

## 댓글 추가

```kotlin
var isLiked: Boolean = false
        val btnLike = findViewById<Button>(R.id.btn_like_onepost)
        btnLike.setOnClickListener {
            // 좋아요 버튼 클릭 시 이미지 변경
            if (isLiked) {
                btnLike.setBackgroundResource(R.drawable.fill_like)
            } else {
                btnLike.setBackgroundResource(R.drawable.like)
            }
            // isLiked 상태 변경
            isLiked = !isLiked
        }
```

```kotlin
val etComment = findViewById<EditText>(R.id.et_comment)
        val btnComment = findViewById<Button>(R.id.btn_comment)
        val tvCommentShow = findViewById<TextView>(R.id.tv_comment_show)
        val tvName = findViewById<TextView>(R.id.tv_name_onepost)
        val ivUser = findViewById<ImageView>(R.id.iv_user_onepost)

        // 초기에는 댓글 입력 요소 및 댓글 표시를 보이지 않도록 설정
        tvName.visibility = View.GONE
        tvCommentShow.visibility = View.GONE
        ivUser.visibility = View.GONE

btnComment.setOnClickListener {
            val commentText = etComment.text.toString().trim()
            if (commentText.isNotEmpty()) {
                // 댓글을 입력했을 때만 댓글 입력 요소 및 댓글 표시를 보이게 변경
                tvName.visibility = View.VISIBLE
                tvCommentShow.visibility = View.VISIBLE
                ivUser.visibility = View.VISIBLE

                // 비어있는 댓글 텍스트에 새로 입력한 댓글 표시
                val existingText = tvCommentShow.text.toString()
                val newComment = "$existingText\n$commentText"
                tvCommentShow.text = newComment

                // 여기서 extras에 저장하는 부분도 추가 가능하나, 잘 안되는듯.. 보수 필요
                intent.putExtra("comment", commentText)
                setResult(Activity.RESULT_OK, intent)
            }
            else {
                // 댓글이 비어있으면 토스트 메시지 띄우기
                Toast.makeText(this, "댓글을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
```

## 메인 화면

```
val health = findViewById<LinearLayout>(R.id.health)
        val music = findViewById<LinearLayout>(R.id.music)
        val fashion = findViewById<LinearLayout>(R.id.fashion)
        val mypage_btn = findViewById<ImageView>(R.id.mypage_btn)
        mypage_btn.setOnClickListener {
            val intent = Intent(this,MyPage::class.java)
            startActivity(intent)
            Toast.makeText(this, "마이페이지로 이동합니다 ", Toast.LENGTH_SHORT).show()
        }
//      //운동게시판
        health.setOnClickListener {
            Toast.makeText(this, "운동게시판으로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Topic1::class.java)
            startActivity(intent)

        }
        //음악게시판
        music.setOnClickListener {
            Toast.makeText(this, "음악게시판으로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,Topic2::class.java)
            startActivity(intent)
        }
        //패션게시판
        fashion.setOnClickListener {
            Toast.makeText(this, "패션게시판으로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,Topic3::class.java)
            startActivity(intent)
        }
```

## 게시판 리스트 추가

```kotlin
//리스트 뷰 항목
        val TopicList = findViewById<ListView>(R.id.TopicList)
        val UserList = arrayListOf<Topic_Item>()
        val Topic_Adpater = Topic_Adpater(this,UserList)
        TopicList.adapter = Topic_Adpater

        UserList.add(Topic_Item("프로틴 내돈내산 리얼후기",""))
        UserList.add(Topic_Item("러닝 코스 best5",""))
        if (intent.getStringExtra("title")==null){
        } else{
            UserList.add(Topic_Item(intent.getStringExtra("title"),""))
        }
```

```jsx
//리스트 제목 데이터값 전달
TopicList.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
            val selectedItem = UserList[position]
            val intent = Intent(this,OnePost::class.java)
            intent.putExtra("selectedItem", selectedItem.title)
            intent.putExtra("context", selectedItem.context.toString())
            startActivity(intent)

        }
```

## 마이페이지

```kotlin
//서버용
        val nameDB = findViewById<TextView>(R.id.name)
        val emailDB = findViewById<TextView>(R.id.id)
        val passDB = findViewById<TextView>(R.id.pass)
        val residenceDB = findViewById<TextView>(R.id.residence)
        val interestingDB = findViewById<TextView>(R.id.interesting)
        val mbtiDB = findViewById<TextView>(R.id.mbti)

        // get request
        val uid = FirebaseAuth.getInstance().uid ?:""
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val currentUserDB = myRef.child("Users").child(uid)

        Log.e("UID",currentUserDB.toString())
        currentUserDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                nameDB.text = snapshot.child("name").value.toString()
                emailDB.text = snapshot.child("id").value.toString()
                passDB.text = snapshot.child("pw").value.toString()
                residenceDB.text = snapshot.child("loc").value.toString()
                interestingDB.text = snapshot.child("like").value.toString()
                mbtiDB.text = snapshot.child("mbti").value.toString()
                globalEmail = snapshot.child("id").value.toString()
                globalName = snapshot.child("name").value.toString()
                globalPass = snapshot.child("pw").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
```
