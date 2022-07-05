//package com.example.rickandmorty.presentation.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.rickandmorty.databinding.ItemSingleBinding
//
//class SingleLocAdapter: RecyclerView.Adapter<SingleLocAdapter.ViewHolder>(){
//    private var singleLocation: List<String> = emptyList()
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleLocAdapter.ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = ItemSingleBinding.inflate(layoutInflater, parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: SingleLocAdapter.ViewHolder, position: Int) {
//        holder.bind(singleLocation[position])
//        holder.itemView.setOnClickListener {
//            onItemClickListener?.let { it(singleLocation[position]) }
//        }
//    }
//
//    override fun getItemCount(): Int = singleLocation.size
//
//    inner class ViewHolder(private val binding: ItemSingleBinding):
//        RecyclerView.ViewHolder(binding.root){
//        fun bind(singleCharacter: String){
//            with(binding){
//                single.text = singleCharacter
//            }
//        }
//    }
//    fun setSingleLoc(singleLoc: List<String>)
//    {
//        singleLocation = singleLoc
//    }
//    private var onItemClickListener: ((String) -> Unit)? = null
//
//    fun setOnClickListener(listener: (String) -> Unit) {
//        onItemClickListener = listener
//    }
//}