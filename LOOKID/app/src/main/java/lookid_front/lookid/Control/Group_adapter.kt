package lookid_front.lookid.Control

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
<<<<<<< HEAD
import lookid_front.lookid.Activity.ResInfo_Activity
import lookid_front.lookid.Entity.Group_Entity
import lookid_front.lookid.R
import lookid_front.lookid.Activity.Reservation_Activity
import lookid_front.lookid.Entity.Admin_Entity
=======
import lookid_front.lookid.Entity.Group_Entity
import lookid_front.lookid.R
import lookid_front.lookid.Activity.Reservation_Activity
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
import org.json.JSONObject

class Group_adapter(val context: Context, val grouplist : ArrayList<Group_Entity>) : RecyclerView.Adapter<Group_adapter.holder>() {
    var child_adapter : Child_adapter = Child_adapter(context)
    var checked_list : ArrayList<Boolean> = arrayListOf<Boolean>(false)
    var child_num_list : ArrayList<Int> = arrayListOf<Int>(0)
    var dialog : AlertDialog? = null
    val textWatcher_ary = arrayListOf<EditListener>()
<<<<<<< HEAD
    var group_state : Boolean = true
    var activity : String = ""
    constructor(context: Context, grouplist: ArrayList<Group_Entity>, group_state : Boolean, activity : String) : this(context, grouplist){
        this.group_state = group_state
        this.activity = activity
=======
    var group_state : Boolean = false
    constructor(context: Context, grouplist: ArrayList<Group_Entity>, group_state : Boolean) : this(context, grouplist){
        this.group_state = group_state
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): holder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_res_group,p0,false)
        for(i in 0 until 10){
            textWatcher_ary.add(EditListener(i))
        }
        return holder(view)
    }
    override fun getItemCount(): Int { return grouplist.size }
    override fun onBindViewHolder(p0: holder, p1: Int) { p0.bind(grouplist[p1],context,p1) }
    inner class holder(view : View) : RecyclerView.ViewHolder(view){
        val name_EditText = view.findViewById<EditText>(R.id.res_group_name_EditText)
        val child_Button = view.findViewById<ImageButton>(R.id.res_group_child_Button)
        val admin_EditText = view.findViewById<EditText>(R.id.res_group_admin_EditText)
        val admin_Button = view.findViewById<Button>(R.id.res_group_idsearch_Button)
        val adminlist_RecView = view.findViewById<RecyclerView>(R.id.res_group_adminlist_RecView)
        val delete_Button = view.findViewById<Button>(R.id.res_group_delete_Button)
<<<<<<< HEAD
        val child_CheckBox = view.findViewById<CheckBox>(R.id.res_group_child_CheckBox)


        fun bind(group : Group_Entity, context : Context, id : Int) {
            if(!group_state){ //변경 불가능
=======

        fun bind(group : Group_Entity, context : Context, id : Int) {
            if(group_state){ //변경 불가능
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
                name_EditText.isEnabled = false
                child_Button.isEnabled = false
                admin_EditText.isEnabled = false
                admin_Button.isEnabled = false
                delete_Button.visibility = View.GONE
<<<<<<< HEAD
                child_CheckBox.visibility = View.INVISIBLE
=======
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
            }
            val index = id
            Log.d("Res_Group","$index + 번째 그룹이 바인드됨")
            val admin_adapter = Admin_adapter(context, grouplist[index].admin_list)
            adminlist_RecView.adapter = admin_adapter
<<<<<<< HEAD
=======
            adminlist_RecView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
            if(index < grouplist.size)
                name_EditText.setText(group.name)
            for(i in 0 until 10)
                name_EditText.removeTextChangedListener(textWatcher_ary[i])
            name_EditText.addTextChangedListener(textWatcher_ary[index])
            child_Button.setOnClickListener(Click_listener(index))
            admin_Button.setOnClickListener(Click_listener(index,admin_EditText,admin_adapter))
            delete_Button.setOnClickListener(Click_listener(index,name_EditText))
<<<<<<< HEAD
            child_CheckBox.isChecked = checked_list[index]
=======
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
        }
    }
    //피보호자 리스트 다이얼로그를 띄워주는 함수
    fun Dialog_child(index : Int){
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("피보호자 목록")
        var inflater  = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_res_childlist,null))
        builder.setPositiveButton("확인",null)
        builder.setNegativeButton("취소",null)
        dialog = builder.create() as AlertDialog
        dialog!!.setOnShowListener(Dialog_Listener(index))
        dialog!!.show()
    }
    //비어있는 그룹을 리스트에 더하고 그룹 개수 제한을 걸어주는 함수
    fun add() {
        if(grouplist.size >= 10){
            Toast.makeText(context,"최대 그룹은 10개 입니다",Toast.LENGTH_SHORT).show()
            return
        }
        checked_list.add(false)
        child_num_list.add(0);
        grouplist.add(Group_Entity(0, arrayListOf(), arrayListOf(),""))
        notifyItemChanged(grouplist.size)
    }
    //네트워크에 GET 방식으로 id를 이용해 관리자를 검색하는 함수
    private fun GET_admin(id : String){
        /*val url = context.getString(R.string.server_url) + context.getString(R.string.find_admin) + id
        asynctask().execute(url)*/ //통신 on 코드

        //admin_adapter.add(Pair(id,1)) //테스트 코드
    }
    //모든 피보호자 수를 가져오는 함수
    fun getDevice_num() : Int{
        var num : Int = 0
        for(i in 0 until grouplist.size)
            num += grouplist[i].child_list.size

        return num
    }

    //어뎁터 클릭 리스너
    inner class Click_listener(val index : Int) : View.OnClickListener{
        var editText : EditText? = null
        var admin_adapter : Admin_adapter? = null
        constructor(index : Int, editText: EditText) : this(index){ this.editText = editText }
        constructor(index : Int, editText: EditText, admin_adapter: Admin_adapter) : this(index){
            this.editText = editText
            this.admin_adapter = admin_adapter
        }

        override fun onClick(v: View) {
            when(v.id){
                R.id.res_group_delete_Button->{
                    if(itemCount == 1){
                        Toast.makeText(context,"그룹은 최소 1개 이상이어야 합니다",Toast.LENGTH_SHORT).show()
                        return
                    }
                    grouplist.removeAt(index)
<<<<<<< HEAD
                    checked_list[index] = false
=======
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
                    notifyItemRemoved(index)
                    notifyItemRangeChanged(index,itemCount)
                    Log.d("Res_Group",index.toString() + "번째 그룹이 삭제됨")
                    Log.d("Res_Group",grouplist.toString())
                }
                R.id.res_group_idsearch_Button->{

                    if(!editText!!.text.isNullOrEmpty()){
<<<<<<< HEAD
                        val id = editText!!.text.toString()
                        //id를 이용 검색
                        admin_adapter!!.add(Admin_Entity(0,id,id))
=======
                        var id = editText!!.text.toString()
                        //id를 이용 검색
                        admin_adapter!!.add(Pair(id,0))
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
                        //GET_admin(id)
                        editText!!.setText("")
                    }
                    else
                        Toast.makeText(context,"아이디를 입력해주세요",Toast.LENGTH_SHORT).show()
                }
                R.id.res_group_child_Button->{ Dialog_child(index) }
            }
        }
    }
    //다이얼로그 리스너
    inner class Dialog_Listener(var index : Int) : DialogInterface.OnShowListener{
        override fun onShow(dialog: DialogInterface?) {
            val alert = dialog as AlertDialog
            val positiveButton : Button = alert.getButton(AlertDialog.BUTTON_POSITIVE)
            val child_Rec = alert.findViewById<RecyclerView>(R.id.dialog_res_child_list_RecView)
            val child_num = alert.findViewById<EditText>(R.id.dialog_res_child_num_EditText)
            val child_button = alert.findViewById<Button>(R.id.dialog_res_child_num_Button)
            child_adapter = Child_adapter(context)
            child_Rec.adapter = child_adapter
            child_Rec.layoutManager = LinearLayoutManager(context)
            child_Rec.setItemViewCacheSize(100)

            if(grouplist[index].child_list.size > 0) {
                child_num.setText(grouplist[index].child_list.size.toString())
                child_adapter.setlist(grouplist[index].child_list)
            }

            positiveButton.setOnClickListener {
                if(child_adapter.checklist()){
                    grouplist[index].child_list = child_adapter.getlist()
                    checked_list.set(index,true)
<<<<<<< HEAD
                    notifyDataSetChanged()
                    //해당 리스너
                    //context로 액태비티 콜하고 해당 함수 실행
                    if(activity.equals("ResInfo")){
                        (context as ResInfo_Activity).ResInfo_Control().pay_init()
                    }
                    else{
                        (context as Reservation_Activity).devicenum = getDevice_num()
                        (context as Reservation_Activity).Reservation_Control().pay_init()
                    }
=======
                    //해당 리스너
                    //context로 액태비티 콜하고 해당 함수 실행
                    (context as Reservation_Activity).devicenum = getDevice_num()
                    (context as Reservation_Activity).Reservation_Control().pay_init()
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
                    alert.dismiss()
                }
            }
            child_button.setOnClickListener {
                if(child_num.text.toString().isEmpty()){
                    Toast.makeText(context,"피보호자 수를 입력해주세요",Toast.LENGTH_SHORT).show()
                }
                else {
                    child_adapter.list_init(child_num.text.toString().toInt())
                }
            }
        }
    }
    inner class EditListener(var index : Int) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if(!s.toString().isEmpty()) {
                Log.d("Res_Group", "그룹 리스트의 $index 번째에 ${s.toString()} 을 넣음")
                if(index < itemCount)
                    grouplist[index].name = s.toString()
            }
            else
                Log.d("Res_Group", "그룹 리스트의 $index 번째에 ${s.toString()} 넣지 않음")
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    inner class numEditListener(val index : Int) : TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            if(!s.isNullOrEmpty())
                child_num_list[index] = s.toString().toInt()
            Log.d("Res_Group","리스너 콜됨")
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }
    inner class asynctask : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg params: String): String {
            //GET_관리자 검색
            val url = params[0]
            return Okhttp(context).GET(url)
        }

        override fun onPostExecute(response: String) {
            if(response.isEmpty()) {
                Log.d("Res_Group", "null")
                return
            }
            if(!Json().isJson(response)){
                Toast.makeText(context,"네트워크 통신 오류", Toast.LENGTH_SHORT).show()
                Log.d("Res_Group", response)
                return
            }
            val json  = JSONObject(response)
            val index = json.getInt("user_pid")
            val name = json.getString("name")
            if(index == 0)
                Toast.makeText(context,"존재하지 않는 아이디입니다",Toast.LENGTH_SHORT).show()
            /*else
                admin_adapter.add(Pair(name, index))*/
        }
    }
}