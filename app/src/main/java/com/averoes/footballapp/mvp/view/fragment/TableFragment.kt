package com.averoes.footballapp.mvp.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.table.TableItem
import com.averoes.footballapp.mvp.presenter.TablePresenter
import com.averoes.footballapp.mvp.view.TableView
import com.averoes.footballapp.utils.TableAdapter
import com.example.footballapp.mvp.model.soccer.CountrysItem
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.FoldingCube
import kotlinx.android.synthetic.main.fragment_table.*
import org.jetbrains.anko.support.v4.ctx


/**
 * A simple [Fragment] subclass.
 *
 */
class TableFragment : androidx.fragment.app.Fragment(), TableView {

    private lateinit var presenter: TablePresenter
    private lateinit var tableAdapter: TableAdapter
    private var item = mutableListOf<TableItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        presenter = TablePresenter(this)
        tableAdapter = TableAdapter(item)

        return inflater.inflate(R.layout.fragment_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val foldingCube:Sprite = FoldingCube()
        loading_table.setIndeterminateDrawable(foldingCube)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val detail = activity?.intent?.getParcelableExtra<CountrysItem>("detail")

        activity?.actionBar?.title = detail?.leagueName
        presenter.getTableTeam(detail?.idLeague.toString())
        table_team.adapter = tableAdapter
        table_team.layoutManager = LinearLayoutManager(ctx)


    }

    override fun showLoading() {

        if (loading_table != null){
            loading_table.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        if (loading_table != null){
            loading_table.visibility = View.GONE
        }
    }

    override fun showMessage(pesan: String) {
    }

    override fun errorMessage(error: String) {
    }

    override fun showMatchList(match: List<TableItem>?) {
        if (match != null){
            item.clear()
            item.addAll(match)
        }
        tableAdapter.notifyDataSetChanged()

    }



}
