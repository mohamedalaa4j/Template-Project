package com.am.template.data.repository

import android.content.Context
import androidx.core.content.ContextCompat
import com.am.template.R
import com.am.template.data.local.models.ReportModel
import com.am.template.domain.repository.Repository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(@ApplicationContext val context: Context) : Repository {

    override suspend fun getListData(): Flow<List<ReportModel>> {

        return flow {
            emit(data())
        }
    }

    private fun data(): List<ReportModel> {
        val list = mutableListOf<ReportModel>()

        list.add(
            ReportModel(
                context.getString(R.string.total_sales),
                ContextCompat.getDrawable(context, R.drawable.ic_total_sales)!!,
                1235.50
            )
        )

        list.add(
            ReportModel(
                context.getString(R.string.net_sales),
                ContextCompat.getDrawable(context, R.drawable.ic_net_sales)!!, 1235.50
            )
        )

        list.add(
            ReportModel(
                context.getString(R.string.electronic_payment),
                ContextCompat.getDrawable(context, R.drawable.ic_electronic_payment)!!, 1235.50
            )
        )

        list.add(
            ReportModel(
                context.getString(R.string.cash_payment),
                ContextCompat.getDrawable(context, R.drawable.ic_cash_payment)!!, 1235.50
            )
        )

        list.add(
            ReportModel(
                context.getString(R.string.total_refund),
                ContextCompat.getDrawable(context, R.drawable.ic_total_refund)!!, 1235.50
            )
        )

        list.add(
            ReportModel(
                context.getString(R.string.total),
                ContextCompat.getDrawable(context, R.drawable.ic_total)!!, 1235.50
            )
        )

        return list
    }
}