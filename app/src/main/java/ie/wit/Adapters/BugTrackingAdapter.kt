package ie.wit.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.R
import ie.wit.models.BugTrackingModel
import kotlinx.android.synthetic.main.card_bug.view.*

class BugTrackingAdapter constructor(private var bugTrackings: List<BugTrackingModel>)
    : RecyclerView.Adapter<BugTrackingAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_bug,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val bugTracking = bugTrackings[holder.adapterPosition]
        holder.bind(bugTracking)
    }

    override fun getItemCount(): Int = bugTrackings.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(bugTracking: BugTrackingModel) {
            itemView.bugFormTitle.text = bugTracking.title
            itemView.bugFormNumber.text = bugTracking.bugimportance
            itemView.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}