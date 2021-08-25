package com.example.healthroutineapplication

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.adapters.WorkOutRoutineAdapter
import com.example.healthroutineapplication.databinding.FragmentMainBinding
import com.example.healthroutineapplication.interfaces.GoActivity
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModel
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModelFactory

class MainFragment(val intent: Intent) : Fragment(), GoActivity {

    private lateinit var binding: FragmentMainBinding

    private val exerciseRoutineViewModel: ExerciseRoutineViewModel by viewModels {
        ExerciseRoutineViewModelFactory((activity?.application as ExerciseRoutineApp).repository)
    }

    private val workOutAdapter = WorkOutRoutineAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)


        with(binding) {
            with(mainRecyclerView) {
                adapter = workOutAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                mainRecyclerView.setHasFixedSize(true)
            }
        }


        exerciseRoutineViewModel.routines.observe(viewLifecycleOwner) { routines ->
            workOutAdapter.setData(routines)
        }

        //drag and drop
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.START, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                exerciseRoutineViewModel.delete(workOutAdapter.removeData(viewHolder.layoutPosition))
            }

            //스와이프 시 색 나오게 함
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val icon: Bitmap
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = (itemView.bottom - itemView.top).toFloat()
                    val width = height / 4
                    val paint = Paint()
                    if (dX < 0) {
                        paint.color = Color.parseColor("#ff0000")
                        val background = RectF(
                            itemView.right.toFloat() + dX,
                            itemView.top.toFloat(),
                            itemView.right.toFloat(),
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, paint)

                        //스와이프 시 아이콘도 나오게 함, img_Asset 사용
                        icon = BitmapFactory.decodeResource(resources, R.drawable.ic_stat_name)
                        val iconDst = RectF(
                            itemView.right.toFloat() -3*width,
                            itemView.top.toFloat() + width,
                            itemView.right.toFloat() -width,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon,null,iconDst,null)
                    }
                }
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.mainRecyclerView)



        return binding.root
    }

    override fun goActivity(id: Long?, name: String?) {
        intent.putExtra("putId", id)
        intent.putExtra("putName", name)
        startActivity(intent)
    }

}