package com.test.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class Modify : AppCompatActivity() {
    lateinit var mypageButton: ImageButton
    lateinit var profileButton: ImageButton
//    lateinit var residence: EditText
//    lateinit var mbti: EditText
//    lateinit var pass: EditText
//    lateinit var interesting: EditText
    lateinit var modificationButton: ImageButton
//    lateinit var slogun: TextView
//    lateinit var name: TextView
//    lateinit var nameIndex: TextView
    lateinit var globalName : String
    lateinit var globalEmail : String
    lateinit var globalPass : String

//    fun deletePost(postId: String, userId: String, callback: (Boolean, String?) -> Unit) {
//        val database = FirebaseDatabase.getInstance()
//        val postsRef = database.getReference("posts").child(postId)
//        val userPostsRef = database.getReference("user-posts").child(userId).child(postId)
//
//        postsRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    postsRef.removeValue { error, _ ->
//                        if (error != null) {
//                            callback(false, "Error deleting post: ${error.message}")
//                        } else {
//                            userPostsRef.removeValue { userError, _ ->
//                                if (userError != null) {
//                                    callback(false, "Error deleting user post: ${userError.message}")
//                                } else {
//                                    callback(true, null)
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    callback(false, "Post not found")
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                callback(false, "Database error: ${error.message}")
//            }
//        })
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify )

        globalName = intent.getStringExtra("name").toString()
        globalEmail = intent.getStringExtra("email").toString()
        globalPass = intent.getStringExtra("pw").toString()

//
//        slogun=findViewById(R.id.slogun)
        val name=findViewById<TextView>(R.id.name)
        name.text = globalName

        val email = findViewById<TextView>(R.id.id)
        email.text = globalEmail

        val pwd = findViewById<TextView>(R.id.pass)
        pwd.text = globalPass


        val loc = findViewById<EditText>(R.id.residence)
        val like = findViewById<EditText>(R.id.interesting)
        val mbti = findViewById<EditText>(R.id.mbti)


        val editLoc = loc.text.toString()
        val editLike = like.text.toString()
        val editMbti = mbti.text.toString()
        if (editLoc.isEmpty() || editLike.isEmpty()|| editMbti.isEmpty()) {
            Toast.makeText(this, "빈칸을 다 채워주세요", Toast.LENGTH_SHORT).show()
        } else {
//            deletePost(postId, userId) { success, errorMessage ->
//                if (success) {
//                    println("Post deleted successfully")
//                } else {
//                    println("Error: $errorMessage")
//                }
//            }
        }
//
//        val slogunText:String=slogun.text.toString()
//        val nameText:String=name.text.toString()
//        val builder= SpannableStringBuilder(slogunText)
//        val builder2= SpannableStringBuilder(nameText)
//        nameIndex=findViewById(R.id.name)
//
//        val nameIndexText=nameIndex.text.toString()
//        val charFind=','
//
//        val index=nameIndexText.indexOf(charFind)
//
//
//        val Wcolor= ForegroundColorSpan(Color.RED)
//        builder.setSpan(Wcolor, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        val ILcolor= ForegroundColorSpan(Color.RED)
//        builder.setSpan(ILcolor, 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        val nameStyle= StyleSpan(Typeface.BOLD)
//        builder2.setSpan(nameStyle,0,index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        val nameSize= RelativeSizeSpan(1.6f)
//        builder2.setSpan(nameSize,0,index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//
//        slogun.text = builder
//        name.text=builder2
//        residence=findViewById(R.id.residence)
//        mbti=findViewById(R.id.mbti)
//        pass=findViewById(R.id.pass)
//        interesting=findViewById(R.id.interesting)





        mypageButton=findViewById(R.id.profileButton)
        mypageButton.setOnClickListener{
            val intent= Intent(this,MyPage::class.java)
            startActivity(intent)
        }

        modificationButton = findViewById(R.id.modificationButton)
        modificationButton.setOnClickListener{
            val intent = Intent(this, MyPage::class.java)
            startActivity(intent)
        }
//        modificationButton.setOnClickListener {
//            val residenceT = residence.text.toString()
//            val mbtiT = mbti.text.toString()
//            val interestingT=interesting.text.toString()
//            val passT=pass.text.toString()
//
//            val result = Intent()
//            result.putExtra("residenceId", residenceT)
//            result.putExtra("mbtiId", mbtiT)
//            Log.d("HUN", "regidence:$residenceT, mbtiT:$mbtiT")
//            result.putExtra("interestingId", interestingT)
//            result.putExtra("passId", passT)
//            setResult(RESULT_OK, result)
//
//            finish()
//        }
    }

}