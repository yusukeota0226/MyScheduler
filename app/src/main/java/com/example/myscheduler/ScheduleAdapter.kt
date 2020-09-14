import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myscheduler.Schedule
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter
import java.text.DateFormat

class ScheduleAdapter(data: OrderedRealmCollection<Schedule>?) : RealmBaseAdapter<Schedule>(data) {
    inner class ViewHolder(cell: View){
        var date = cell.findViewById<TextView>(android.R.id.text1)
        var title = cell.findViewById<TextView>(android.R.id.text2)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        when(convertView){
            null -> {
                val inflater = LayoutInflater.from(parent?.context)
                view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
                viewHolder = ViewHolder(view)
                view.tag = viewHolder
            }
            else -> {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }
        }

//        adapterData?.run {
//            val schedule = get(position)
//            viewHolder.date.text =
//                DateFormat.format("yyyy/MM/dd", schedule.date)
////                    DateFormat.format("yyyy/MM/dd", schedule.date)
////            dateText.setText(android.text.format.DateFormat.format("yyyy/MM/dd", c ))
//            viewHolder.title.text = schedule.title
//        }
        return view
    }

}