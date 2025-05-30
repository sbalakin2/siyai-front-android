package com.example.siyai_front_android.presentation.main.home_container.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.di.ViewModelFactory
import com.example.siyai_front_android.presentation.model.Categories
import com.example.siyai_front_android.presentation.model.Product
import com.example.siyai_front_android.presentation.model.Regularity
import com.example.siyai_front_android.presentation.model.TrackList
import com.example.siyai_front_android.presentation.model.User
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    navigateToSignOfTheDayScreen: () -> Unit,
    navigateToFreeLessonsScreen: () -> Unit,
    navigateToAddTrackScreen: () -> Unit,
    navigateToArchiveScreen: () -> Unit,
    navigateToWaitingListScreen: () -> Unit,
    navigateToProductDetailScreen: (Product) -> Unit
) {
    val viewModel: HomeViewModel = viewModel(factory = viewModelFactory)
    val homeState by viewModel.homeState.collectAsStateWithLifecycle()

    when (homeState) {
        is HomeState.Success -> {
            var screenState by remember { mutableStateOf(state) }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    HeaderSection(
                        modifier = Modifier.padding(top = 16.dp),
                        firstName = (homeState as HomeState.Success).profile.firstName
                    )
                }

                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    SignOfTheDay(onClick = navigateToSignOfTheDayScreen)
                }

                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    FreeLessonsCard(onClick = navigateToFreeLessonsScreen)
                }

                TrackListsBlock(
                    trackLists = screenState.trackLists,
                    onCheckTrack = {},
                    onAddTrack = navigateToAddTrackScreen,
                    goToArchive = navigateToArchiveScreen
                )

                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    WaitingListBanner(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = navigateToWaitingListScreen
                    )
                }

                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    ShopTabs(
                        modifier = Modifier.padding(vertical = 16.dp),
                        selectedCategory = screenState.selectedCategory,
                        onCategoryChange = { screenState = screenState.copy(selectedCategory = it) }
                    )
                }

                items(screenState.products) { product ->
                    ProductGridItem(
                        item = product,
                        onClick = { navigateToProductDetailScreen(product) }
                    )
                }
            }
        }
        is HomeState.Error -> {

        }
        is HomeState.Exception -> {

        }
        HomeState.Loading -> {

        }
    }
}

private data class HomeScreenState(
    val user: User,
    val selectedCategory: Categories,
    val trackLists: List<TrackList>,
    val products: List<Product>
)

private val state = HomeScreenState(
    user = User(name = "username"),
    selectedCategory = Categories.ALL,
    trackLists = listOf(
        TrackList(1, "спать", Regularity.EVERY_DAY, 89, false),
        TrackList(2, "бегать", Regularity.EVERY_DAY, 7, true, progress = 1)
    ),
    products = listOf(
        Product(imageId = R.drawable.product_1, price = 39990, name = "SIYAI New Year"),
        Product(imageId = R.drawable.product_2, price = 990, name = "Вебинары"),
        Product(imageId = R.drawable.product_3, price = 9990, name = "Дом Сияй онлайн"),
        Product(imageId = R.drawable.product_4, price = 18990, name = "Практики SIYAI"),
        Product(imageId = R.drawable.product_5, price = 99900, name = "SIYAI Premium"),
        Product(imageId = R.drawable.product_6, price = 44999, name = "SIYAI 11.Любовь"),
        Product(imageId = R.drawable.product_7, price = 99999, name = "SIYAI New Level")
    )
)

@Composable
@Preview
private fun HomeScreen_Preview() {
    SiyaifrontandroidTheme {
        HomeScreen(
            viewModelFactory = ViewModelFactory(mapOf()),
            navigateToSignOfTheDayScreen = { /*TODO*/ },
            navigateToFreeLessonsScreen = { /*TODO*/ },
            navigateToAddTrackScreen = { /*TODO*/ },
            navigateToArchiveScreen = { /*TODO*/ },
            navigateToWaitingListScreen = { /*TODO*/ },
            navigateToProductDetailScreen = { /*TODO*/ }
        )
    }
}