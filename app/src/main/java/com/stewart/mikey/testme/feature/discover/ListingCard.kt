package com.stewart.mikey.testme.feature.discover

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.stewart.mikey.testme.R
import com.stewart.mikey.testme.core.domain.Listing
import com.stewart.mikey.testme.ui.theme.BluffOyster600
import com.stewart.mikey.testme.ui.theme.BluffOyster800
import com.stewart.mikey.testme.ui.theme.TestMeTheme

@Composable
fun ListingCard(listing: Listing) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                Toast
                    .makeText(
                        context,
                        "Listing details not implemented",
                        Toast.LENGTH_LONG,
                    )
                    .show()
            }
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = listing.pictureHref,
            modifier = Modifier
                .size(ImageSize)
                .background(Color.LightGray),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column {
            Text(
                text = listing.region,
                style = TertiaryTextStyle,
            )
            Text(
                text = listing.title,
                style = SecondaryTextStyle,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.weight(1f))
            if (listing.isClassified) {
                ClassifiedPriceSection(listing.priceDisplay)
            } else {
                AuctionPriceSection(
                    priceDisplay = listing.priceDisplay,
                    buyNowPrice = if (listing.hasBuyNow) listing.buyNowPrice else null
                )
            }
        }
    }
}

@Composable
fun ClassifiedPriceSection(priceDisplay: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = priceDisplay,
                style = PrimaryTextStyle,
            )
            Text(
                text = stringResource(R.string.label_asking_price),
                style = TertiaryTextStyle,
            )
        }
    }
}

@Composable
fun AuctionPriceSection(priceDisplay: String, buyNowPrice: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = priceDisplay,
                style = PrimaryTextStyle,
            )
            Text(
                text = stringResource(R.string.label_no_reserve),
                style = TertiaryTextStyle,
            )
        }
        buyNowPrice?.run {
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = this@run,
                    style = PrimaryTextStyle,
                )
                Text(
                    text = stringResource(R.string.label_buy_now),
                    style = TertiaryTextStyle,
                )
            }
        }
    }
}

private val ImageSize = 96.dp
private val PrimaryTextStyle: TextStyle
    @Composable get() = MaterialTheme.typography.labelLarge.copy(
        color = BluffOyster800,
        fontWeight = FontWeight.Bold,
    )
private val SecondaryTextStyle: TextStyle
    @Composable get() = MaterialTheme.typography.labelMedium.copy(
        color = BluffOyster800,
    )
private val TertiaryTextStyle: TextStyle
    @Composable get() = MaterialTheme.typography.labelSmall.copy(
        color = BluffOyster600,
    )

@Preview
@Composable
private fun ListingItemPreview(
    @PreviewParameter(ListingPreviewParameterProvider::class) listing: Listing
) {
    TestMeTheme {
        ListingCard(listing)
    }
}

/**
 * Provides different variations of Listing data.
 */
class ListingPreviewParameterProvider : PreviewParameterProvider<Listing> {
    override val values = sequenceOf(
        Listing(
            listingId = 0,
            pictureHref = "https://random.dog/13973-10200-28695.jpg",
            region = "Gotham City",
            title = "2012 BATMOBILE SPORT GT ***LOW K'S + ONE CAREFUL OWNER*** BULLETPROOF SPEC",
            isClassified = true,
            priceDisplay = "$15,999.00",
            buyNowPrice = "0.0",
            hasBuyNow = false
        ),
        Listing(
            listingId = 1,
            pictureHref = "https://random.dog/13973-10200-28695.jpg",
            region = "Hyrule",
            title = "Master Sword !!!LIKE NEW!!!",
            isClassified = false,
            priceDisplay = "$267.50",
            buyNowPrice = "$399.00",
            hasBuyNow = true
        ),
        Listing(
            listingId = 2,
            pictureHref = "https://random.dog/13973-10200-28695.jpg",
            region = "Kanto Region",
            title = "Leaf Stone **UNUSED!**",
            isClassified = false,
            priceDisplay = "$99.00",
            buyNowPrice = "0.0",
            hasBuyNow = false
        ),
    )
}
