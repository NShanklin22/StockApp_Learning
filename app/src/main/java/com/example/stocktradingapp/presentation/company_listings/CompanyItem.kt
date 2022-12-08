package com.example.stocktradingapp.presentation.company_listings

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocktradingapp.domain.model.CompanyListing

@Composable
fun CompanyItem(
    company: CompanyListing,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            modifier = Modifier.weight(1f)
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = company.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = company.exchange,
                    style = TextStyle(
                        fontWeight = FontWeight.Light
                    ),
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "(${ company.symbol})",
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle.Italic
                    ),
                    color = MaterialTheme.colors.onBackground
                )
            }
        }

    }
}